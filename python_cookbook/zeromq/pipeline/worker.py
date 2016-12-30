# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
# Task worker
# Connects PULL socket to tcp://localhost:5557
# Collects workloads from ventilator via that socket
# Connects PUSH socket to tcp://localhost:5558
# Sends results to sink via that socket
#
# Author: Lev Givon <lev(at)columbia(dot)edu>

import sys
import time
import zmq
import os

context = zmq.Context()

# Socket to receive messages on
receiver = context.socket(zmq.PULL)
receiver.connect("tcp://localhost:5557")

# Socket to send messages to
sender = context.socket(zmq.PUSH)
sender.connect("tcp://localhost:5558")

# Process tasks forever
while True:
    s = receiver.recv()

    # Simple progress indicator for the viewer
    sys.stdout.write("received task:"+s.split("_")[0]+" will elasped "+str(int(s.split("_")[1])*0.001)+"\n")
    sys.stdout.flush()
    # Do the work
    time.sleep(int(s.split("_")[1])*0.001)
    message="worker pid:"+str(os.getpid())+" task:"+s.split("_")[0]
    # Send results to sink
    sender.send(message)
