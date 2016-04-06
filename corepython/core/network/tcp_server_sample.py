# -*- coding: utf-8 -*-
from SocketServer import BaseRequestHandler, TCPServer, StreamRequestHandler, ThreadingTCPServer
import traceback

__author__ = 'dell'
'''演示如何使用TcpServer实现网络编程，当服务启动后可以通过telnet的方式向下面的服务发出请求测试'''
class UpperRequestHandler(BaseRequestHandler):
    """
    #从BaseRequestHandler继承，并重写handle方法
    """
    def handle(self):
        #循环监听（读取）来自客户端的数据
        while True:
            #当客户端主动断开连接时，self.recv(1024)会抛出异常
            try:
                #一次读取1024字节,并去除两端的空白字符(包括空格,TAB,\r,\n)
                data = self.request.recv(1024).strip()

                #self.client_address是客户端的连接(host, port)的元组
                print "receive from (%r):%r" % (self.client_address, data)

                #转换成大写后写回(发生到)客户端
                self.request.sendall(data.upper())
            except:
                traceback.print_exc()
                break


class StreamUpperRequestHandler(StreamRequestHandler):
    """
   使用TCPServer和StreamRequestHandler编写socket服务的样例。
   StreamRequestHandler从BaseRequestHandler，并做了封装，使得读写数据更容易。
    """
    def handle(self):
        while True:
            #客户端主动断开连接时，self.rfile.readline()会抛出异常
            try:
                #self.rfile类型是socket._fileobject,读写模式是"rb",方法有
                #read,readline,readlines,write(data),writelines(list),close,flush
                data = self.rfile.readline().strip()
                print "receive from (%r):%r" % (self.client_address, data)

                #self.wfile类型是socket._fileobject,读写模式是"wb"
                self.wfile.write(data.upper())
            except :
                traceback.print_exc()
                break


if __name__=="__main__":
    addr=("localhost",9999)
    # server=TCPServer(addr,StreamUpperRequestHandler)
    #ThreadingTCPServer 可以达到多线程的运行效果
    server = ThreadingTCPServer(addr, StreamUpperRequestHandler)
    server.serve_forever()