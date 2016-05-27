# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
import traceback
import inspect, itertools

def simple_decorator(func):
    def func_wrapper():
        try:
            print "before"
            func()
            print "after"
        except Exception,e:
            print e
    return func_wrapper

def inspect_decorator(func):
    def wrapper(*args, **kwargs):
        # if you want arguments names as a list:
        args_name = inspect.getargspec(func)[0]
        print("args name:"+str(args_name))

        # if you want names and values as a dictionary:
        args_dict = dict(itertools.izip(args_name, args))
        print("args dict:"+str(args_dict))

        # if you want values as a list:
        args_values = args_dict.values()
        print("args value:"+str(args_values))

        result=func(*args)
        print "result:%s" %(result)


    return wrapper


class LogWrappedFunction(object):
    def __init__(self, function):
        self.function = function

    def logAndCall(self, *arguments, **namedArguments):
        print "Calling %s with arguments %s and named arguments %s" %\
                      (self.function.func_name, arguments, namedArguments)
        try:
            result=self.function.__call__(*arguments, **namedArguments)
        except:
            traceback.print_exc()

        print "Execute %s result is %s" %(self.function.func_name,result)

def logwrap(function):
    return LogWrappedFunction(function).logAndCall


