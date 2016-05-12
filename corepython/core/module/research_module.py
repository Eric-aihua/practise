# -*- coding:utf-8 -*-
'''演示如何获取模块有哪些功能'''
__author__ = 'eric.sun'

import sys
from  pprint import pprint
import copy
import doc_test_module

def dir_sample():
    #打印所有sys的特性
    pprint(dir(sys))

    #只打印外部可以使用的
    pprint([feature for feature  in dir(sys) if not feature.startswith("_")])

def all_sample():
    print [feature for feature  in dir(copy) if not feature.startswith("_")]
    #定义了共有接口，即通过from copy import * 导入的内容。
    # 如果在模块中不定义__all__,则 from module import * 会导入所有不以__开头的特性
    print copy.__all__

def help_sample():
    #help(copy)
    # doc_test_module.__doc__
    help(doc_test_module)

def file_sample():
    '''查找import模块的路径'''
    print copy.__file__

# dir_sample()
# all_sample()
# help_sample()
file_sample()