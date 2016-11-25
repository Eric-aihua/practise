# encoding:utf-8

import csv
import re
import lxml
from zipfile import ZipFile

from StringIO import StringIO


class CSVScrapeCallBack:
    """
    用于处理下载html的回调函数，当下载html成功时，使用该文件对html解析，并存到csv文件中
    """

    def __init__(self):
        self.writer = csv.writer(open('countries.csv', 'w'))
        self.fields = (
            'area', 'population', 'iso', 'country', 'capital', 'continent', 'tld', 'currency_code', 'currency_name',
            'phone', 'postal_code_format', 'postal_code_regex', 'languages', 'neighbours')
        self.writer.writerow(self.fields)

    def __call__(self, url, html):
        if re.search('/view/', url):
            tree = lxml.html.fromstring(html)
            row = []
            for field in self.fields:
                column = tree.cssselect('table > tr#places_{}__row > td.w2p_fw'.format(field))
                if column:
                    row.append(column[0].text_content())
                else:
                    row.append(None)

            self.writer.writerow(row)


class ProxyCSVCallBack:
    """
    用于处理下载html的回调函数，当下载html成功时，使用该文件对html解析，并存到csv文件中
    """

    def __init__(self):
        self.writer = csv.writer(open('countries.csv', 'w'))
        self.fields = (
            'area', 'population', 'iso', 'country', 'capital', 'continent', 'tld', 'currency_code', 'currency_name',
            'phone', 'postal_code_format', 'postal_code_regex', 'languages', 'neighbours')
        self.writer.writerow(self.fields)

    def __call__(self, url, html):
        if re.search('/view/', url):
            tree = lxml.html.fromstring(html)
            row = []
            for field in self.fields:
                column = tree.cssselect('table > tr#places_{}__row > td.w2p_fw'.format(field))
                if column:
                    row.append(column[0].text_content())
                else:
                    row.append(None)

            self.writer.writerow(row)


class AlexaCallback:
    def __init__(self, max_urls=5000):
        self.max_urls = max_urls
        self.seed_url = 'http://s3.amazonaws.com/alexa-static/top-1m.csv.zip'

    def __call__(self, url, html):
        if url == self.seed_url:
            urls = []
            # with ZipFile(StringIO(html)) as zf:
            with ZipFile(open('/home/eric/Downloads/top-1m.csv.zip')) as zf:
                web_site_list_file = zf.namelist()[0]
                print web_site_list_file
                for _, websize in csv.reader(zf.open(web_site_list_file)):
                    if len(urls) == self.max_urls:
                        break
                    else:
                        urls.append(websize)
            # print len(urls)
            return urls



# def parse_test():
#
#     with ZipFile(open('/home/eric/Downloads/top-1m.csv.zip')) as zf:
#         web_site_list_file = zf.namelist()[0]
#         for _, websize in csv.reader(zf.open(web_site_list_file)):
#             print websize

# parse_test()