# encoding:utf-8
import cookielib
# import mechanize
# import urllib as urlxxx
import urllib
import  urllib2
import  cookielib
#from urllib.error import URLError, HTTPError
import json,time
# from lxml import etree

__author__ = 'eric.sun'

indexUrl = 'http://m.hbcpic.com/vote/index.aspx?empno=WUHW4208&from=timeline'
voteUrl = 'http://m.hbcpic.com/services/vote.ashx'
parameters = {"action": "VotePlayer", 'postEmpNo': 'WUHW4208'}

# def flush_vote(user_agent):
#     # url = 'http://m.hbcpic.com/vote/index.aspx?empno=WUHW4208&from=timeline'
#
#     br = mechanize.Browser()
#     cj = cookielib.LWPCookieJar()
#     cj.clear_session_cookies()
#     br.set_cookiejar(cj)
#     br.addheaders([
#                         ('User-Agent', user_agent),
#                         ('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8'),
#                         ('Accept-Language', 'en-gb,en;q=0.5'),
#                         ('Accept-Charset', 'ISO-8859-1,utf-8;q=0.7,*;q=0.7'),
#                         ('Keep-Alive', '115'),
#                         ('Connection', 'keep-alive'),
#                         ('Cache-Control', 'max-age=0'),
#                     ])
#     data = urllib.urlencode(parameters)
#     response = br.open(indexUrl, data)
#     print response
#     print "投票成功！"

def flush_vote2(user_agent,proxy):
    print proxy
    for i in range(1):
    #cookie持有
#cj = http.cookiejar.CookieJar()
        cj = cookielib.CookieJar()
        opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
        proxy=urllib2.ProxyHandler(proxies=proxy)
        opener.add_handler(proxy)

        urllib2.install_opener(opener)
        # req=urllib2.Request(indexUrl)
        # urllib2.urlopen(req)
        #设置参数
        import random
        rano=random.randint(1,1000)
        parameters['userAgent']=str(rano)+"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) dsd Chrome/31.0.1650.63 Safari/537.36 SE 2.X MetaSr 1.0"
        parameterencode=urllib.urlencode(parameters)
        parameterencode=parameterencode.encode('UTF-8')
        resp2= opener.open(voteUrl,parameterencode)
        # print("请求完毕,{0}".format(resp2))
        jsonData = json.loads(resp2.read().decode('utf-8'))
        print(i,jsonData['Msg'])
        time.sleep(1)

from random import choice
user_agents = ['Mozilla/5.0 (X11; U; Linux; i686; en-US; rv:1.6) Gecko Debian/1.6-7']
# random_user_agent = choice(user_agents)


def get_proxy_list():
    url='https://free-proxy-list.net/'



def auto_proxy():
    proxy_list=open('/home/eric/Downloads/proxy_list.txt')
    for proxy_line in proxy_list:
        host=proxy_line.split('\t')[0]
        port= proxy_line.split('\t')[1]

        for i in user_agents:
            proxys={'http':'http://%s:%s'%(host,port)}
            try:
                flush_vote2(i,proxys)
            except BaseException ,e:
                print e



auto_proxy()
