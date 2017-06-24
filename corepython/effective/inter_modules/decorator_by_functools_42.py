# encoding:utf-8
from functools import wraps

__author__ = 'eric.sun'

'''演示如何通过functools.wraps 创建正确的装饰器'''


# 使用普通方法定义一个trace装饰器
def normal_trace(func):
    def wrap(*args,**kargs):
        """wrap的doc"""
        result=func(*args,**kargs)
        print '函数：%s 输入：%s 输出:%s'%(func.__name__,args,kargs)
        return result
    return wrap

@normal_trace
def fibonacci(n):
    """返回斐波纳契数"""
    if n in (0,1):
        return n
    return (fibonacci(n-2)+fibonacci(n-1))


# 使用functools.wraps方法定义一个trace装饰器
def functools_trac(func):
    #多一个functools.wraps的定义
    @wraps(func)
    def wrap(*args,**kargs):
        """wrap的doc"""
        result=func(*args,**kargs)
        print '函数：%s 输入：%s 输出:%s'%(func.__name__,args,kargs)
        return result
    return wrap

@functools_trac
def fibonacci2(n):
    """返回斐波纳契数"""
    if n in (0,1):
        return n
    return (fibonacci(n-2)+fibonacci(n-1))

def normal_decrator():
    # 使用此种方式对fibonacci进行装饰时，实际执行的是将fibonacci原函数作为参数传给了normal_trace方法，并将normal_trace的返回值赋值给了fibonacci变量,既fibonacci=normal_trace(fibonacci)
    # 所以此时原fibonacci函数的信息都会丢失，打印__doc__以及__name__时，显示的都是wrap的定义
    print fibonacci.__doc__
    print fibonacci.__name__
    print fibonacci(4)

def functools_decrator():
    # 使用了functools.wraps定义的装饰器后，fibonacci2的相关属性得到了保留
    print fibonacci2.__doc__
    print fibonacci2.__name__
    print fibonacci2(4)

if __name__ == '__main__':
    normal_decrator()
    functools_decrator()

