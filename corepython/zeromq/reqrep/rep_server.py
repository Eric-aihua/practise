# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
import zmq
import time
from common import time_utils

context = zmq.Context()
socket = context.socket(zmq.REP)
socket.bind("tcp://*:5555")
count = 0

while True:
    #  Wait for next request from client
    message = socket.recv()
    count += 1
    print "Received request: ", message, count

    #  Do some 'work'
    time.sleep (1)        #   Do some 'work'

    #  Send reply back to client
    socket.send(time_utils.get_format_timestamp()+" World")
