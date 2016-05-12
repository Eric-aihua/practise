# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
import zmq
import time
from common import time_utils

context = zmq.Context()
socket = context.socket(zmq.PUB)
socket.bind("tcp://*:5555")

while True:
    #  Wait for next request from client
    message = time_utils.get_format_timestamp()+":Publish message"
    print "Publish Message: ", message
    #  Do some 'work'
    time.sleep (1)        #   Do some 'work'
    #  Send reply back to client
    socket.send(message)
