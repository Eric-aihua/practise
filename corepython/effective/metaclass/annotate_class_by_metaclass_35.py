# encoding:utf-8
__author__ = 'eric.sun'

"""通过descriptor加上metadata特性，实现自动创建属性以及设置属性名称的功能"""

class FieldMeta(type):
    def __new__(meta, name, bases, class_dict):
        for k, v in class_dict.items():
            # 此处用来自动设置internal_name 的名称，而不需要再显示进行设置了
            if isinstance(v, Field):
                v.name = k
                v.internal_name = '_' + v.name
        return type.__new__(meta, name, bases, class_dict)


# 该descripror 主要是为了将对Field对象中对name的属性的复制，传导internal_name上
class Field(object):

    def __init__(self):
        self.name = None
        self.internal_name = None

    def __get__(self, instance, owner):
        if instance is None: return ''
        return getattr(instance, self.internal_name, '')

    def __set__(self, instance, value):
        setattr(instance, self.internal_name, value)


class DataBaseRow(object):
    __metaclass__ = FieldMeta

class BetterDatabase(DataBaseRow):
    #对比单纯的使用descriptor特性，此处不需要再构造Field的时候，传入名称，而是由FieldMeta来完成,descriptor的例子参见reuse_property_by_descriptor_31
    first_name=Field()
    last_name=Field()
    age=Field()



def annotate_by_metaclass():
    bd=BetterDatabase()
    print bd.__dict__
    bd.first_name='sun'
    print bd.__dict__

    bd2=BetterDatabase()
    print bd2.__dict__
    bd2.first_name='zhang'
    print bd2.__dict__

if __name__ == '__main__':
    annotate_by_metaclass()