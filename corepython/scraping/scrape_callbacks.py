# encoding:utf-8

import csv
import re
import lxml


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
                column=tree.cssselect('table > tr#places_{}__row > td.w2p_fw'.format(field))
                if column:
                    row.append(column[0].text_content())
                else:
                    row.append(None)

            self.writer.writerow(row)
