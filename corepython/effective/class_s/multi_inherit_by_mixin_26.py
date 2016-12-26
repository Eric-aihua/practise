# encoding:utf-8
__author__ = 'eric.sun'

"""mix-in 类：是指只提供了一些通用功能而不定义对象属性的类。
   在需要多重继承时，建议使用mix-in类，继承类也不要求调用父类的__init__构造器

   本例主要通过实现一个可以将对象转换为字典的mix-in类，来说明mix-in类的使用
"""


class ToDictMixIn(object):
    def to_dict(self):
        return self._traverse_dict(self.__dict__)

    def _traverse_dict(self, attributes):
        output = {}
        for name, attr_type in attributes.items():
            output[name] = self._traverse(attr_type)
        return output

    def _traverse(self, v):
        if isinstance(v, ToDictMixIn):
            return v.to_dict()
        if isinstance(v, dict):
            return self._traverse(v)
        if isinstance(v, list):
            return [self._traverse(i) for i in v]
        if hasattr(v, '__dict__'):
            return self._traverse_dict(v)
        else:
            return v


class BinaryTree(ToDictMixIn):
    def __init__(self, value, left=None, right=None):
        """
        由于ToDictMixin式Mixin类，所以此处不需要调用ToDictMixin的__init__构造函数
        :param value:
        :param left:
        :param right:
        """
        self.value = value
        self.left = left
        self.right = right


# 演示将BinaryTree对象转为dict
def binary_object_todict():
    obj=BinaryTree(10,left=BinaryTree(7,right=BinaryTree(9)),right=BinaryTree(13,left=BinaryTree(11)))
    print obj.to_dict()


if __name__ == '__main__':
    binary_object_todict()
