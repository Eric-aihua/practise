# coding:utf-8
"""本文件演示如果抓取页面"""

import urllib2
import traceback
import logging
import re

import itertools
import urlparse

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')

# TEST_URL = 'http://example.webscraping.com/'
TEST_URL = 'http://127.0.0.1:8000/places/'
TEST_SITE_MAP_URL = 'http://example.webscraping.com/sitemap.xml'
TEST_BASE_ID_URL = 'http://127.0.0.1:8000/places/default/view/%d'
# TEST_SITE_MAP_URL = 'http://192.168.116.131:8000/places/default/sitemap.xml'
DEFAULT_AGENT = 'Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0'


def download(page_url, user_agent=DEFAULT_AGENT, number_retries=2):
    # logging.info('Downloading url:%s' % page_url)
    html = None
    try:
        headers = {'User-agent': user_agent}
        requests = urllib2.Request(page_url, headers=headers)
        html = urllib2.urlopen(requests).read()
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


def link_download(base_url, link_regex):
    """
    按照regex的定义,递归link进行下载,效果最好
    :param base_url:基本的URL
    :param link_regex:满足递归下载的URL表达式
    """
    crawl_queue = [base_url]
    # linked 主要用来记录已经下载过的url,防止重复下载
    linked = set(crawl_queue)
    while crawl_queue:
        url = crawl_queue.pop()
        html = download(url)
        for html_link in get_links(html):
            if re.findall(link_regex, html_link):
                full_url = urlparse.urljoin(base_url, html_link)
                if full_url not in linked:
                    linked.add(full_url)
                    crawl_queue.append(full_url)
