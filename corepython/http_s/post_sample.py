# encoding:utf-8
import cookielib
import mechanize
import urllib as urlxxx
import  urllib2 as urllib
import  cookielib
#from urllib.error import URLError, HTTPError
import json,time
# from lxml import etree

__author__ = 'eric.sun'

indexUrl = 'http://m.hbcpic.com/vote/index.aspx?empno=WUHW4208&from=timeline'
voteUrl = 'http://m.hbcpic.com/services/vote.ashx'
parameters = {"action": "VotePlayer", 'postEmpNo': 'WUHW4208'}

def flush_vote(user_agent):
    # url = 'http://m.hbcpic.com/vote/index.aspx?empno=WUHW4208&from=timeline'

    br = mechanize.Browser()
    cj = cookielib.LWPCookieJar()
    cj.clear_session_cookies()
    br.set_cookiejar(cj)
    br.addheaders([
                        ('User-Agent', user_agent),
                        ('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8'),
                        ('Accept-Language', 'en-gb,en;q=0.5'),
                        ('Accept-Charset', 'ISO-8859-1,utf-8;q=0.7,*;q=0.7'),
                        ('Keep-Alive', '115'),
                        ('Connection', 'keep-alive'),
                        ('Cache-Control', 'max-age=0'),
                    ])
    data = urllib.urlencode(parameters)
    response = br.open(indexUrl, data)
    print response
    print "投票成功！"

def flush_vote2(user_agent):
    for i in range(10):
    #cookie持有
#cj = http.cookiejar.CookieJar()
        cj = cookielib.CookieJar()
        opener = urllib.build_opener(urllib.HTTPCookieProcessor(cj))
        #proxy=urllib.request.ProxyHandler({'sock5':'183.207.228.9:80'})
        #opener.add_handler(proxy)
        urllib.install_opener(opener)
        req=urllib.Request(indexUrl)
        resp= urllib.urlopen(req)
        # print(resp.getheader("Set-Cookie"))
        # print(resp.getheaders())
        # print(cj)
        #设置参数
        import random
        rano=random.randint(1,1000)
        parameters['userAgent']=str(rano)+"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) dsd Chrome/31.0.1650.63 Safari/537.36 SE 2.X MetaSr 1.0"
        parameterencode=urlxxx.urlencode(parameters)
        #print(parameterencode)
        parameterencode=parameterencode.encode('UTF-8')
#print(parameterencode)
        #request=urllib.request.Request(voteUrl,parameterencode)
        resp2= opener.open(voteUrl,parameterencode)
        #resp2= urllib.request.urlopen(voteUrl,parameterencode)
        # print("请求完毕,{0}".format(resp2))
        jsonData = json.loads(resp2.read().decode('utf-8'))
        print(i,jsonData['Msg'])
        time.sleep(1)


from random import choice
user_agents = ['Mozilla/5.0 (X11; U; Linux; i686; en-US; rv:1.6) Gecko Debian/1.6-7','Konqueror/3.0-rc4; (Konqueror/3.0-rc4; i686 Linux;;datecode)','Opera/9.52 (X11; Linux i686; U; en)']
# random_user_agent = choice(user_agents)
for i in user_agents:
    flush_vote2(i)
