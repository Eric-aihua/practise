# encoding:utf-8
import abc

__author__ = 'eric.sun'

"""演示如何强制要求子类实现抽象方法"""

class ParentClass(object):
    # __metaclass__用来强制要求子类实现某个方法
    __metaclass__ = abc.ABCMeta
    def __init__(self):
        print 'parent class'

    @abc.abstractmethod
    def walk(self):
        raise NotImplementedError

class SubClass(ParentClass):
    def __init__(self):
        super(SubClass,self).__init__()
        print 'initing '

if __name__ == '__main__':
    subClass=SubClass()