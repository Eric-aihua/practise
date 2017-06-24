# encoding:utf-8

__author__ = 'eric.sun'

import json

"""构建程序时，类的注册是一种很有用的模式，
但是子类容易忘记对类进行注册，此时可以通过__metaclass__机制来实现自动注册
"""

register = {}


def register_class(target_class):
    register[target_class.__name__] = target_class


def deserialize(data):
    params = json.loads(data)
    class_name = params['class']
    target_class = register[class_name]
    return target_class(*params['args'])


class SeriableMetaClass(type):
    def __new__(meta, name, bases, class_dict):
        # print name
        cls = type.__new__(meta, name, bases, class_dict)
        # print cls
        # Serizable的子类必须调用register_class方法，用metaclass机制，可以防止忘记注册
        register_class(cls)
        return cls


# 该类的子类统一转换成json字符串，也可以通过json字符串，转成子类对象
class Serizable(object):
    __metaclass__ = SeriableMetaClass

    def __init__(self, *args):
        self.args = args

    def serialize(self):
        return json.dumps({'class': self.__class__.__name__, 'args': self.args})

    def __repr__(self):
        return 'Object:' + str(self.args)


class Point2DObject(Serizable):
    def __init__(self, x, y):
        super(Point2DObject, self).__init__(x,y)
        self.x = x
        self.y = y



def register_class_by_metaclass():
    obj=Point2DObject(5,6)
    print "序列化之前的对象："+str(obj)
    json_str=obj.serialize()
    print "序列化的字符串："+json_str
    obj2=deserialize(json_str)
    print "反列化之前的对象："+str(obj2)


if __name__ == '__main__':
    register_class_by_metaclass()