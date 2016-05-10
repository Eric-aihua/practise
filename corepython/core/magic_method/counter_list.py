# -*- coding=utf-8 -*-
__metaclass__=type
'''
主要演示如何__getitem__,__len__,__setitem__,__delitem__的用法
'''
__author__ = 'eric.sun'


#统计调用getItem的次数
class CounterList(list):
    def __int__(self,*args):
        super(CounterList, self).__init__(*args)
        self.counter=0

    def __getitem__(self, item):
        self.counter+= 1
        return super(CounterList, self).__getitem__(item)

    def print_count(self):
        print self.counter



def main():
    count_list=CounterList(range(10))
    print count_list
    # print count_list[2]
    # print count_list[3]
    count_list.print_count()

main()