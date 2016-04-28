#-*- coding:utf-8 -*-
__author__ = 'eric.sun'

#只会被调用一次
def iterator():
    print "was call"
    return range(5)

for i in iterator():
    print i