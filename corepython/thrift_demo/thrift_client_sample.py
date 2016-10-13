# -*- coding: utf-8 -*-
from thrift import Thrift
from thrift.protocol import TBinaryProtocol
from thrift.transport import TSocket, TTransport

from thrift_demo.HelloService import HelloService

__author__ = 'eric.sun'


def main():
    try:
        # Make socket
        transport = TSocket.TSocket('127.0.0.1', 9090)

        # Buffering is critical. Raw sockets are very slow
        transport = TTransport.TBufferedTransport(transport)

        # Wrap in a protocol
        protocol = TBinaryProtocol.TBinaryProtocol(transport)

        # Create a client to use the protocol encoder
        client = HelloService.Client(protocol)

        # Connect!
        transport.open()

        client.sayHello()
        print client.getData("Eric")
        transport.close()
    except Exception,e:
        print e
if __name__ == '__main__':
    main()
