# coding:utf-8
"""本文件演示如果抓取页面"""

import urllib2
import traceback
import logging
import re

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')

TEST_URL = 'http://example.webscraping.com/'
TEST_SITE_MAP_URL = 'http://192.168.116.131:8000/places/default/sitemap.xml'
DEFAULT_AGENT = 'Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0'


def download(page_url, user_agent=DEFAULT_AGENT, number_retries=2):
    logging.info('Downloading url:%s' % page_url)
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
    sitemap_content = download(sitemap_url)
    links = re.findall('<loc>(.*?)</loc>', sitemap_content)
    result={}
    for link in links:
        page=download(link)
        logging.info('Site map url:%s' %link)
        result[link]=page
    return result





