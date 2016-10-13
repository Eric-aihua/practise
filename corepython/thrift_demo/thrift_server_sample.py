# -*- coding: utf-8 -*-
import sys
sys.path.append("HelloService")
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

from thrift_demo.HelloService.HelloService import Processor

"""
安装编译器：apt-get install thrift-compiler
生成Server 代码：eric@eric-desktop:~/sourcecode/git/practise/corepython/thrift_demo$ thrift  -out ./ --gen py HelloService.thrift
该文件负责启动Server
"""


class HelloServiceHandler:
    def __init__(self):
        self.log = {}
        print "init...."

    def sayHello(self ):
        print "SayHello from server...."

    def getData(self, element):
        return "get data from server......"


if __name__ == '__main__':
    try:
        handler = HelloServiceHandler()
        processor = Processor(handler)
        transport = TSocket.TServerSocket(host='127.0.0.1', port=9090)
        tfactory = TTransport.TBufferedTransportFactory()
        pfactory = TBinaryProtocol.TBinaryProtocolFactory()

        server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

    # You could do one of these for a multithreaded server
    # server = TServer.TThreadedServer(processor, transport, tfactory, pfactory)
    # server = TServer.TThreadPoolServer(processor, transport, tfactory, pfactory)

        print 'Starting the server...'
        server.serve()
        print 'done.'
    except Exception as e:
        print e
