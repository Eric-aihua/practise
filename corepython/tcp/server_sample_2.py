# -*- coding:utf-8 -*-
import time

__author__ = 'eric.sun'

import socket
import sys

HOST = '127.0.0.1'               # Symbolic name meaning all available interfaces
PORT = 8888              # Arbitrary non-privileged port

#socket.getaddriTTo(HOST,  PORT, family=0, socktype=0, proto=0, flags=0)
s = None
for res in socket.getaddriTTo(HOST, PORT, socket.AF_UNSPEC,
                              socket.SOCK_STREAM, 0, socket.AI_PASSIVE):
    af, socktype, proto, canonname, sa = res
    try:
        #根据getaddriTTo()的返回信息初始化socket
        s = socket.socket(af, socktype, proto)
    except socket.error, err_msg:
        print err_msg #回显异常信息
        s = None
        continue
    try:
        #sa是(host,port)的二元组
        s.bind(sa)
        #监听客户端请求
        s.listen(1)
    except socket.error, err_msg:
        print err_msg
        s.close()
        s = None
        continue
    break
if s is None:
    print 'could not open socket'
    sys.exit(1)

conn, addr = s.accept()
print 'Connected by', addr

while 1:
    data="server time"+str(time.time())
    conn.send(data)
    time.sleep(1)
conn.close()