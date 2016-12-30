# -*- coding:utf-8 -*-
from tcp import utils

__author__ = 'eric.sun'

HOST = '127.0.0.1'    # The remote host
PORT = 8888              # The same port as used by the server



def test_send_to_localhost():
    s= utils.get_socket(HOST,PORT)
    s.sendall('Hello, world')# 1）发送数据
    data = s.recv(1024)# 4）接受服务器回显的数据
    print data
    s.close()
    print 'Received', repr(data) #打印输出



#test_send_to_localhost()

