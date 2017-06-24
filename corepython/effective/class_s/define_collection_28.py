# encoding:utf-8
__author__ = 'eric.sun'

from collections import Sequence

"""Python中很多collection的使用便捷都是通过语法糖来达到的，例如一个类实现了__getitem__方法，就能实现obj[index]
   的效果，但是实现一个collection经常会有很多的方法要实现。
   为了避免遗漏实现的方法，可以继承collections.XXX抽象类，实现完整的collection功能
"""

class BadSubClass(Sequence):
    '''在实例化BadSubClass的时候，会抛出TypeError: Can't instantiate abstract class BadSubClass with abstract methods __getitem__, __len__
       子类必须实现特定的抽象方法
    '''
    def __init__(self):
        print 'init bad sub class'

if __name__ == '__main__':
    bad_sub_class=BadSubClass()
