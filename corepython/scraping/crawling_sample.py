# coding:utf-8
"""本文件演示如果抓取页面"""

import urllib2
import traceback
import logging

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')

TEST_URL = 'http://example.webscraping.com/'


def download_page(page_url):
    logging.info('Downloading url:%s' % page_url)
    html = None
    try:
        html = urllib2.urlopen(page_url).read()
    except urllib2.URLError, e:
        traceback.print_exc(e)
        logging.error("Downloading url:%s failed" % page_url)
    logging.info('Downloading url:%s successful' % page_url)
    return html


if __name__ == '__main__':
    print download_page(TEST_URL)
