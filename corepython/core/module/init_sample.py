# -*- coding:utf-8 -*-
__author__ = 'eric.sun'

#调用module包中__init__文件中的内容,引入__init__
from __init__ import module_init_func
module_init_func()


#调用其他包中__init__文件中的内容,引入包名
from core.function import other_package_init_func
other_package_init_func()