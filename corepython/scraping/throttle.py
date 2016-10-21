# encoding:utf-8
import urlparse
import datetime
import time
import logging

__author__ = 'eric.sun'


logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')


class Throttle():
    def __init__(self, delay):

        """
        构建爬虫限速的对象，有的网站如果发现某个客户端访问过于频繁，可能会封掉客户端的IP,所以访问此类的网站就需要做限速
        :param delay:对相同domain的访问时间间隔，0代表不限制
        """
        self.delay = delay
        self.domains = {}

    def wait(self, url):

        domain = urlparse.urlparse(url).netloc
        last_access_time = self.domains.get(domain,0)
        if self.delay > 0 and last_access_time:
            sleep_time = self.delay - (datetime.datetime.now() - last_access_time).seconds

            if sleep_time > 0:
                logging.info("try to access url:%s but must sleep" %url)
                time.sleep(sleep_time)
        self.domains[domain] = datetime.datetime.now()


if __name__ == '__main__':
    throttle=Throttle(0)
    throttle.wait("www.baidu.com")
    throttle.wait("www.baidu.com")
    throttle.wait("www.baidu.com")



