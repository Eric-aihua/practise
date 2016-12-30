# encoding:utf-8
import urllib
import urllib2
import cookielib
<<<<<<< HEAD
=======
import json, time

from scraping.basic.crawling_utils import link_download
from scraping.basic.scrape_callbacks import ProxyCSVCallBack

>>>>>>> 83c9a8fa460479b931d929f1eecd80de93efbc10
__author__ = 'eric.sun'

indexUrl = 'http://m.hbcpic.com/vote/index.aspx?empno=WUHW4208&from=timeline'
voteUrl = 'http://m.hbcpic.com/services/vote.ashx'
success_count = 0
parameters = {"action": "VotePlayer", 'postEmpNo': 'WUHW4208'}



def flush_vote2(proxy_info):
    # print proxy
    for i in range(1):
        cj = cookielib.CookieJar()
        opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
        proxy = urllib2.ProxyHandler(proxies=proxy_info)
        opener.add_handler(proxy)
        urllib2.install_opener(opener)
        # 设置参数
        import random

        rano = random.randint(1, 1000)
        parameters['userAgent'] = str(
            rano) + "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) dsd Chrome/31.0.1650.63 Safari/537.36 SE 2.X MetaSr 1.0"
        parameterencode = urllib.urlencode(parameters)
        parameterencode = parameterencode.encode('UTF-8')
        resp2 = opener.open(voteUrl, parameterencode, timeout=5)
        jsonData = json.loads(resp2.read())
        s = jsonData['Msg']
        print(i, s)
        if jsonData['Status']:
            print 'Successful:%s' % proxy_info
            # success_count += 1
        else:
            print 'Duplicate:%s' % proxy_info
        time.sleep(1)


user_agents = ['Mozilla/5.0 (X11; U; Linux; i686; en-US; rv:1.6) Gecko Debian/1.6-7']


# random_user_agent = choice(user_agents)



def generate_proxy_list():
    url=''
    scrape_call_back=ProxyCSVCallBack()
    link_download(url, link_regex='/(index|view)/', max_depth=5, scrape_call_back=scrape_call_back)


def auto_proxy():
    proxy_list=generate_proxy_list()
    # proxy_list = open('./proxy_list.txt')
    for proxy_line in proxy_list:
        split_result = proxy_line.split('\t')
        host = split_result[0]
        port = split_result[1]
        if len(split_result) > 6:
            is_https = split_result[6] or None

        for i in user_agents:
            if is_https == 'no':
                protocal = 'http'
            else:
                protocal = 'https'
            proxys = {protocal: '%s://%s:%s' % (protocal, host, port)}
            try:
                flush_vote2(proxys)
            except BaseException, e:
                print e

# 通过自动代理，实现自动投票的功能
auto_proxy()
