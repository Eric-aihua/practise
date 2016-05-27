# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
from decorator.decorators import *

def simple_func():
    print "hello world"

@simple_decorator
def simple_func2():
    print "hello world2"

@logwrap
def add(x,y):
    return x+y

@logwrap
def add2(x,y):
    print x+y

@inspect_decorator
def add3(x,y):
    return x+y

if __name__ == '__main__':
    #使用显示的包装类对数据进行处理
    aop_obj=simple_decorator(simple_func)
    # aop_obj()

    #使用注解的方式进行数据处理
    # simple_func2()

    #使用获取数据参数以及结果的方式对数据进行处理
    # print add(3,4)
    # print add2(3,4)

    #使用inspect的方式对方法调用进行监测
    add3(5,6)

