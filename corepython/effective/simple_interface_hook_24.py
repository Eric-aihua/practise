# encoding:utf-8
__author__ = 'eric.sun'
import collections


"""本代码主要演示如何对简单的接口传入hook"""


class DefaultDictCountMissing():
    def __init__(self):
        self.count = 0

    def __call__(self):
        """
        实现了__call__方法，该类的对象是callable的，可以直接作为函数hook
        :return:
        """
        self.count += 1
        return '-1'


def callable_class_as_hook():
    sub_dict = {'read': '1', 'green': '2'}
    full_dict = {'read': '1', 'green': '2', 'blue': '3', 'orange': '4'}
    count = DefaultDictCountMissing()
    # 当从sub_dict中获取key时，DefaultDictCountMissing 的__call__方法会返回默认值，并进行缺失key计数
    result = collections.defaultdict(count, sub_dict)
    for key,_ in full_dict.items():
        print result[key]

    print 'Missing Key In sub_dict:',count.count


def lambda_func_as_hook():
    """
    使用匿名函数作为sort的hook函数，实现在排序时以len(x)为排序依据

    """
    strs = " Details about 'object', use 'object??' for extra details.".split(' ')
    strs.sort(key=lambda x: len(x))
    print strs


if __name__ == '__main__':
    lambda_func_as_hook()
    callable_class_as_hook()
