# -*- coding:utf-8 -**
__author__ = 'sunaihua'

def process_message(*message):
    print 'B',type(message)
    for msg in message:
        print msg


def add_messages(*messages):
    print 'A',type(messages)
    process_message(messages)
    process_message(*messages)


add_messages(1,2,3,4)