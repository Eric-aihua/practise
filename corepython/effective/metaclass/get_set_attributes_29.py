# encoding:utf-8
__author__ = 'eric.sun'

"""本代码主要演示，在对属性进行get,set操作过程中，如果有特殊要求，应该怎么做"""


class Person(object):
    def __init__(self, f_name, l_name):
        self.f_name = f_name
        self.l_name = l_name

    @property
    def full_name(self):
        return '%s %s' % (self.f_name, self.l_name)

#通过property 实现getter,setter功能
class Fees(object):
    def __init__(self):
        self._fee=None

    @property
    def fee(self):
        return self._fee

    @fee.setter
    def fee(self,value):
        if isinstance(value,float) or isinstance(value,int):
            self._fee=value
        else:
            raise TypeError('Value must be a Float or Int')

if __name__ == '__main__':
    # 通过property实现对已有属性的组合
    person = Person('Mike', 'Driscoll')
    print person.full_name
    # 由于该属性不是真实存在的数据，不能进行赋值
    # person.full_name='Jim Sun'

    #通过property 实现getter,setter功能
    fees=Fees()
    fees.fee=3
    print fees.fee


    #由于在setter中做了类型判断，所以传入错误的类型会报错
    fees2=Fees()
    fees2.fee='abc'
    print fees.fee


