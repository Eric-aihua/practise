# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
import zmq

context = zmq.Context()

#  Socket to talk to server
print "Connecting to hello world server..."
socket = context.socket(zmq.SUB)
socket.connect ("tcp://localhost:5555")
socket.setsockopt(zmq.SUBSCRIBE, '')

#  Do 10 requests, waiting each time for a response
while True:
    #print "Sending request ", request,"...

    #  Get the reply.
    message = socket.recv()
    print "Receive Message:"+message
