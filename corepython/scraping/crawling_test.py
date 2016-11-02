# encoding:utf-8
from scraping.basic.scrape_callbacks import CSVScrapeCallBack, AlexaCallback
from scraping.basic.mongo_cache import MongoCache

__author__ = 'eric.sun'

from scraping.basic.crawling_utils import *


def test_simple_donwload():
    """
    最简单的下载

    """
    print download(TEST_URL)


def test_user_agent():
    """
    有些网站对于user-agent为空的可能会禁止访问，必须要加长agent的信息

    """
    # 下面的访问会返回HTTPError: HTTP Error 403: Forbidden
    # download('http://meetup.com',user_agent=None)

    # 下面的访问使用了user-agent,可以正常访问
    download('http://meetup.com')


def test_download_from_sitemap():
    size_map_download(TEST_SITE_MAP_URL)


def test_download_by_id():
    increase_id_download(TEST_BASE_ID_URL)


def test_download_by_link(url, regex):
    link_download(url, regex)


def test_download_by_link_blocked(url, regex):
    link_download(url, regex, 'BadCrawler')


def test_extract_data_by_lxml(url, regex):
    htmls = link_download(url, regex)
    # print "len htmls:%d" % htmls.__sizeof__()
    for html in htmls:
        print 'process html'
        scrape_result = scrape_by_beautiful_soup(str(html))
        print scrape_result


def test_extract_data_by_csv_callback(url, regex):
    link_download(url, regex, max_depth=5, scrape_call_back=CSVScrapeCallBack())


def test_extract_data_by_mongo_cache(url,cache, regrex=None,scrape_call_back=CSVScrapeCallBack()):

    print link_download(url, link_regex=regrex,max_depth=5, scrape_call_back=scrape_call_back, cache=cache)


if __name__ == '__main__':
    # test_simple_donwload()
    # test_user_agent()
    # test_download_from_sitemap()
    # test_download_by_link(TEST_URL, '/(index|view)/')
    # test_download_by_link_blocked(TEST_URL, '/(index|view)/')
    #     test_extract_data_by_lxml('http://127.0.0.1:8000/places/default/view/Albania-3', '/(index|view)/')
    # test_extract_data_by_lxml(TEST_URL, '/(index|view)/')
    # test_extract_data_by_csv_callback(TEST_URL, '/(index|view)/')
    # test_extract_data_by_mongo_cache(TEST_URL, MongoCache(),regrex='/(index|view)/')

    #该测试需要将robots的检测去掉
    alexaCallback = AlexaCallback()
    test_extract_data_by_mongo_cache(alexaCallback.seed_url,scrape_call_back=alexaCallback,cache= MongoCache())
