#  -*- coding: utf-8 -*-
__author__ = 'dell'


def zip_sample():
    list1=range(10)
    list2=range(20,30,1)
    print list1
    print list2
    for x,y in zip(list1,list2):
        print x,y
