# -*- coding=utf-8 -*-
__author__ = 'eric.sun'

class UtilsClass:

    def __init__(self):
        self.class_prop="eric"

    #静态方法，不需要self参数
    @staticmethod
    def static(x,y):
        print "static method was called!"
        #可以访问类成员方法
        UtilsClass.class_s()
        return x+y

    @classmethod
    def class_m(cls):
        #直接访问静态方法
        print UtilsClass.static(3,4)

    @classmethod
    def class_s(cls):
        print "class method was called"


if __name__ == '__main__':
    print UtilsClass.static(3,5)
    UtilsClass.class_m()
    utils=UtilsClass()
    print utils.class_prop
    #用对象调用静态方法
    print utils.static(3,3)