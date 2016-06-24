# -*- encoding:utf-8-*-
'''端口扫描'''
import optparse
import os
import socket
from threading import activeCount, Thread
import traceback

__author__ = 'eric.sun'


def scanning(host, port):
    os.system('title '+str(port))
    cli_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        indicator = cli_sock.connect_ex((host, port))
        if indicator == 0:
            print '[+] host:%s port:%s connect successful\n'%(host,port)
        else:
            pass
            #print '[-] host:%s port:%s connect failed\n'%(host,port)
        cli_sock.close()
    except:
        traceback.print_exc()


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
                # print activeCount()
                Thread(target=scanning,args=(options.full_host,port)).start()
                port+=1


            #scanning(options.full_host,port)
    else:
        parser.print_help()



