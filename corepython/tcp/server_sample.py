# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
#根据给定的参数host/port，相应的转换成一个包含用于创建socket对象的五元组，
#参数host为域名，以字符串形式给出代表一个IPV4/IPV6地址或者None.
#参数port如果字符串形式就代表一个服务名，比如“http”"ftp""email"等，或者为数字，或者为None
#参数family为地主族，可以为AF_INET  ，AF_INET6 ，AF_UNIX.
#参数socketype可以为SOCK_STREAM(TCP)或者SOCK_DGRAM(UDP)
#参数proto通常为0可以直接忽略
#参数flags为AI_*的组合，比如AI_NUMERICHOST，它会影响函数的返回值
#附注：给参数host,port传递None时建立在C基础，通过传递NULL。
#该函数返回一个五元组(family, socktype, proto, canonname, sockaddr)，同时第五个参数sockaddr也是一个二元组(address, port)
# Echo server program
import socket
import sys

HOST = '127.0.0.1'               # Symbolic name meaning all available interfaces
PORT = 8888              # Arbitrary non-privileged port

#socket.getaddrinfo(HOST,  PORT, family=0, socktype=0, proto=0, flags=0)
s = None
for res in socket.getaddrinfo(HOST, PORT, socket.AF_UNSPEC,
                              socket.SOCK_STREAM, 0, socket.AI_PASSIVE):
    af, socktype, proto, canonname, sa = res
    try:
        #根据getaddrinfo()的返回信息初始化socket
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
    data = conn.recv(1024)# 2）接受数据
    if not data: break
    data=">>>>>>>>>>>>:"+data
    conn.send(data)# 3）并返回2中接受到得数据

conn.close()