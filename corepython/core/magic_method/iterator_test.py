# -*- coding=utf-8 -*-
__author__ = 'eric.sun'

#可以生成斐波那契数的迭代器
class Fibs:
    def __init__(self):
        self.a=0
        self.b=1
    #每次迭代都会被调用
    def next(self):
        print "next method was called"
        self.a, self.b=self.a+self.b,self.a
        return self.a

    def __iter__(self):
        return self;

if __name__ == '__main__':
    fibs=Fibs();
    for i in fibs:
        if i >1000:
            break;
        else:
            print i;

