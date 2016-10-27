# coding:utf-8
"""本文件演示如果抓取页面"""
import itertools
import logging
import lxml.html
import re
import robotparser
import traceback
import urllib2
import urlparse

from bs4 import BeautifulSoup

import throttle
from downloader import Downloader

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')

TEST_ROBOTS_URL = 'http://example.webscraping.com/robots.txt'
# TEST_URL = 'http://example.webscraping.com/'
TEST_URL = 'http://127.0.0.1:8000/places/'
# TEST_URL = 'http://192.168.116.131:8000/places/'


# TEST_SITE_MAP_URL = 'http://example.webscraping.com/sitemap.xml'
TEST_SITE_MAP_URL = 'http://192.168.116.131:8000/places/default/sitemap.xml'

TEST_BASE_ID_URL = 'http://127.0.0.1:8000/places/default/view/%d'
DEFAULT_AGENT = 'Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0'

rp = robotparser.RobotFileParser()
rp.set_url(TEST_ROBOTS_URL)

throttle_obj = throttle.Throttle(-1)


def scrape_by_beautiful_soup(html):
    soup = BeautifulSoup(html)
    td = None
    tr = soup.find(attrs={'id': 'places_area__row'})
    if tr:
        td = tr.find(attrs={'class': 'w2p_fw'})
    if td:
        return td.text
    else:
        return None


def scrape_by_lxml(html):
    area = None
    tree = lxml.html.fromstring(html)
    td_list = tree.cssselect('tr#places_population__row > td:nth-child(2)')
    # print td_list
    if td_list:
        td = td_list[0]
        area = td.text_content()
    return area


def download(page_url, user_agent=DEFAULT_AGENT, proxy=None, number_retries=2):
    # logging.info('Downloading url:%s' % page_url)
    html = None
    try:
        # 用于限制对某个domain的访问频率
        throttle_obj.wait(page_url)
        if rp.can_fetch(user_agent, page_url):
            headers = {'User-agent': user_agent}
            requests = urllib2.Request(page_url, headers=headers)
            opener = urllib2.build_opener()
            if proxy:
                # 使用代理
                proxy_parameters = {urlparse.urlparse(page_url).scheme: proxy}
                opener.add_handler(urllib2.ProxyHandler(proxy_parameters))
            html = opener.open(requests).read()
        else:
            logging.warn("URL:%s was blocked by robots.txt" % page_url)
    except urllib2.URLError, e:
        traceback.print_exc(e)
        if hasattr(e, 'code') and 500 <= e.code < 600:
            if number_retries > 0:
                logging.info("Server internal error,try again...")
                return download(page_url, number_retries - 1)
        logging.error("Downloading url:%s failed" % page_url)
        return None
    logging.info('Downloading url:%s successful' % page_url)
    return html


def size_map_download(sitemap_url):
    """
    使用site_map 文件进行下载，site_map中的内容可能不全
    :param sitemap_url:
    :return:
    """
    sitemap_content = download(sitemap_url)
    links = re.findall('<loc>(.*?)</loc>', sitemap_content)
    result = {}
    for link in links:
        page = download(link)
        logging.info('Site map url:%s' % link)
        result[link] = page
    return result


def increase_id_download(base_url, max_errors=10):
    """
    适用与URL格式固定，且ID连续的URL,如果ID无序，则不适用
    :param base_url:
    :param max_errors:
    """
    current_errors = 0
    for item_id in itertools.count(1):
        url = base_url % item_id
        html = download(url)
        if not html:
            current_errors += 1
            if current_errors == max_errors:
                logging.info("stop to search url")
                break
        else:
            current_errors = 0


def get_links(html):
    # logging.info(html)
    link_regex = re.compile('<a[^>]+href=["\'](.*?)["\']', re.IGNORECASE)
    return link_regex.findall(html)


def link_download(base_url, link_regex=None, deplay=-1, user_agent=DEFAULT_AGENT, max_depth=1, scrape_call_back=None,
                  proxies=None, num_retries=None, cache=None):
    """
    按照regex的定义,递归link进行下载,效果最好
    :param proxies:
    :param num_retries:
    :param cache:使用cache对download 的html进行缓存
    :param user_agent:
    :param deplay:
    :param scrape_call_back: 下载页面成功的回调函数，在回调函数中对页面进行解析和存储
    :param base_url:基本的URL
    :param link_regex:满足递归下载的URL表达式
    :param max_depth:从一个连接开始递归爬取的最大深度
    """
    crawl_queue = [base_url]
    rp = get_robots(base_url)
    # linked 主要用来记录已经下载过的url,防止重复下载
    linked = set(crawl_queue)
    # 用于记录每个url的深度
    seen = {}
    while crawl_queue:
        url = crawl_queue.pop()

        # if not rp.can_fetch(user_agent, url):
        #     continue

        url_depth = seen.get(url, 0)
        # html = download(url, user_agent)
        downloader = Downloader(delay=deplay, user_agent=user_agent, proxies=proxies, num_retries=num_retries,
                                cache=cache)
        logging.debug(url)
        html = downloader(url)
        if scrape_call_back:
            scrape_call_back(url, html)
        # print html
        if url_depth != max_depth:
            links = get_links(html)
            if link_regex:
                finally_links = [link for link in links if re.findall(link_regex, link)]
            else:
                finally_links = links
            for html_link in finally_links:
                # if re.findall(link_regex, html_link):
                full_url = urlparse.urljoin(base_url, html_link)
                if full_url not in seen:
                    seen[full_url] = url_depth + 1
                    if full_url not in linked:
                        linked.add(full_url)
                        seen[html_link] = url_depth + 1
                        crawl_queue.append(full_url)


def get_robots(url):
    """Initialize robots parser for this domain
    """
    rp = robotparser.RobotFileParser()
    rp.set_url(urlparse.urljoin(url, '/robots.txt'))
    rp.read()
    return rp
