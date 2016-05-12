# -*- coding:utf-8 -*-
'''
进程通信：When you start making multithreaded applications with ZeroMQ,
you'll encounter the question of how to coordinate your threads.
Though you might be tempted to insert "sleep" statements,
or use multithreading techniques such as semaphores or mutexes,
the only mechanism that you should use are ZeroMQ messages.
Remember the story of The Drunkards and The Beer Bottle.
Let's make three threads that signal each other when they are ready.
In this example, we use PAIR sockets over the inproc transport:'''
__author__ = 'eric.sun'

import threading
import zmq

def step1(context=None):
    """Step 1"""
    context = context or zmq.Context.instance()
    # Signal downstream to step 2
    sender = context.socket(zmq.PAIR)
    sender.connect("inproc://step2")
    message="step1 ready"
    sender.send(message)


def step2(context=None):
    """Step 2"""
    context = context or zmq.Context.instance()
    # Bind to inproc: endpoint, then start upstream thread
    receiver = context.socket(zmq.PAIR)
    receiver.bind("inproc://step2")

    # Wait for signal
    msg = receiver.recv()
    print msg
    # Signal downstream to step 3
    sender = context.socket(zmq.PAIR)
    sender.connect("inproc://step3")
    message="step2 ready"
    sender.send(message)

def step3(context=None):
    # Prepare our context and sockets
    context = zmq.Context.instance()
    # Bind to inproc: endpoint, then start upstream thread
    receiver = context.socket(zmq.PAIR)
    receiver.bind("inproc://step3")
    # Wait for signal
    string = receiver.recv()
    print string
    print "step3 ready"
    print("Test successful!")
    receiver.close()
    context.term()


def main():
    """ server routine """

    thread1 = threading.Thread(target=step1)
    thread1.start()

    thread2 = threading.Thread(target=step2)
    thread2.start()

    thread3 = threading.Thread(target=step3)
    thread3.start()




if __name__ == "__main__":
    main()
