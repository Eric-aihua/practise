# -*- encoding:utf-8-*-
'''端口扫描'''
import optparse
import os
from threading import activeCount, Thread,Semaphore
import traceback
import nmap
__author__ = 'eric.sun'

screen_lock=Semaphore(value=1)

def scanning(host, port):
    try:
	nm=nmap.PortScanner()
	scan_result=nm.scan(host,str(port))
	#PORT可以使用n-m的方式，一次检测多个端口
	print '[+] host:%s port:%s connect successful'%(host,port)
	#返回的结果是Json的字符串，需要按照特定的格式进行解析
	final_result=result['scan'][host]['tcp'][int(port)]['state']
        print '    [-] con message: %s'%(final_result)
    except Exception ,e:
	print traceback.print_exc()
	screen_lock.acquire()
        print '[-] host:%s port:%s connect failed'%(host,port)
    finally:
	screen_lock.release()


if __name__ == '__main__':
    parser=optparse.OptionParser("Port Scaning....")
    parser.add_option('-p',dest='port',type='string',help='special a port')
    parser.add_option('-d',dest='host',type='string',help='special a host')
    parser.add_option('-f',dest='full_host',type='string',help='scanning a host all port')
    options,args=parser.parse_args()
    #单机单端口扫描
    if options.port!=None and options.host!=None:
        scanning(options.host,int(options.port))
    #单机所有接口扫描
    if options.full_host!=None:
        port=1
        while port<65535:
            if activeCount()<200:
                Thread(target=scanning,args=(options.full_host,port)).start()
                port+=1

    if not options:
        parser.print_help()


