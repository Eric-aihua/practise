# encoding:utf-8
__author__ = 'eric.sun'
from pprint import pprint

"""主要说明使用错误的初始化父类方法可能带来的问题，以及如何使用super方式解决之前的问题"""


class BaseClass(object):
    def __init__(self, value):
        self.value = value


class IncorrectTimesFiveDiamondClass(BaseClass):
    def __init__(self, value):
        BaseClass.__init__(self, value)
        self.value *= 5


class IncorrectPlusTwoDiamondClass(BaseClass):
    def __init__(self, value):
        BaseClass.__init__(self, value)
        self.value += 2


class CorrectTimesFiveDiamondClass(BaseClass):
    def __init__(self, value):
        super(CorrectTimesFiveDiamondClass, self).__init__(value)
        self.value *= 5


class CorrectPlusTwoDiamondClass(BaseClass):
    def __init__(self, value):
        super(CorrectPlusTwoDiamondClass, self).__init__(value)
        self.value += 2


class IncorrectSubClass2(IncorrectTimesFiveDiamondClass, IncorrectPlusTwoDiamondClass):
    def __init__(self, value):
        """
        # 该问题数据钻石继承问题，子类有多个父类，每个父类都有相同的父类，使用下面的方式进行初始化，会导致BaseClass被初始化两次的情况
        :param value:
        """
        IncorrectTimesFiveDiamondClass.__init__(self, value)
        IncorrectPlusTwoDiamondClass.__init__(self, value)


class CorrectSubClass2(CorrectTimesFiveDiamondClass, CorrectPlusTwoDiamondClass):
    def __init__(self, value):
        """
        # 通过使用super解决钻石继承问题
        :param value:
        """
        super(CorrectSubClass2,self).__init__(value)

if __name__ == '__main__':
        s1 = IncorrectSubClass2(3)
        print "期望值是17,实际值是：", s1.value

        #使用super关键字时，且有多个父类的时候，调用父类初始化的顺序式按照mro的顺序来的(从下往上)
        pprint(CorrectSubClass2.mro())
        s2 = CorrectSubClass2(3)
        print "期望值是25,实际值是：", s2.value