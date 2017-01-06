# encoding:utf-8
__author__ = 'eric.sun'
"""主要演示如何使用__getattr__,__setattr__,__getattribute__来实现自定义的属性访问方法。
由于演示代码中，需要访问的属性都是没有进行声明的，所以也可以理解为惰性初始化方法
"""


class LazyDB(object):
    def __init__(self):
        self.dbname = 'test'

    # 当访问一个不在obj.__dict__里时，会调用该方法,
    def __getattr__(self, item):
        print '访问属性：%s' % item
        value = 'Value for %s' % item
        # 将新属性加到obj.__dict__中
        setattr(self, item, value)
        return value


class ValidatingDB(object):
    def __init__(self):
        self.dbname = 'test'

    def __getattribute__(self, item):
        print '访问属性：%s' % item
        try:
            # 由于__getattribute__每次访问属性时都会被访问，所以该方法先做个判断
            return super(ValidatingDB, self).__getattribute__(item)
        except:
            value = 'Value for %s' % item
            # 将新属性加到obj.__dict__中
            setattr(self, item, value)
            return value


class LoggingServiceDB(object):
    # 每次设置属性时，该方法都会被调用
    def __setattr__(self, key, value):
        print '设置属性：%s 值:%s' % (key, value)
        super(LoggingServiceDB, self).__setattr__(key, value)


class SetInstanceDB(object):
    def __init__(self):
        # 如果覆盖了__setattr__方法，则在__init__方法中，初始化实例属性时，使用self.__dict__['attr']=XXX的方式
        # self.datadict = {}
        self.__dict__['datadict']={}

    # 每次设置属性时，该方法都会被调用
    def __setattr__(self, key, value):
        print '设置属性：%s 值:%s' % (key, value)
        # super(SetInstanceDB, self).__setattr__(key, value)
        self.datadict[key] = value

class GetInstanceDB(object):
    def __init__(self):
        self.datadict = {'name':'admin'}

    def __getattribute__(self, item):
        print '访问属性：%s' % item
        # 直接访问self.datadict时，会调用__getattribute__,
        # 然而在__getattribute__中又去调用self.datadict.形成了死循环，会出现RuntimeError: maximum recursion depth exceeded 问题
        # return self.datadict[item]

        #如果有访问实例属性的需求，必须使用super()获取属性
        data_dict=super(GetInstanceDB,self).__getattribute__('datadict')
        return data_dict[item]


# __getattribute__演示
def getgetattribute_sample():
    data = ValidatingDB()
    print data.__dict__
    print data.url
    print data.url
    print data.__dict__


# __getattr__演示
def getattr_sample():
    data = LazyDB()
    print data.__dict__
    print data.url
    print data.__dict__


#__setattr__演示
def setattr_sample():
    data = LoggingServiceDB()
    data.url = '10.5.24.32'
    data.username = 'admin'
    data.pwd = 'admin'
    data.pwd = 'admin123'
    print data.__dict__


#__setattr__中设置instance属性的演示
def set_instance_attr_sample():
    data = SetInstanceDB()
    print data.__dict__
    data.url = '10.5.24.32'
    data.username = 'admin'
    data.pwd = 'admin'
    data.pwd = 'admin123'
    print data.__dict__

# 使用__getattribute__方法获取成员属性
def get_instance_attr_sample():
    get=GetInstanceDB()
    print get.name


if __name__ == '__main__':
    # getattr_sample()
    # getgetattribute_sample()
    # setattr_sample()
    # set_instance_attr_sample()
    get_instance_attr_sample()