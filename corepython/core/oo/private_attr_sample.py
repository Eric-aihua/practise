# encoding:utf-8
__author__ = 'eric.sun'


class ParentClass(object):
    def __init__(self):
        self.__value = '10'

if __name__ == '__main__':
    p=ParentClass()
    print dir(p)
    # 私有属性__value 的名称变成了_ParentClass__value,可以通过obj._ParentClass__value 进行访问
    print p._ParentClass__value