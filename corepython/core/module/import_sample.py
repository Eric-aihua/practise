# -*- coding:utf-8 -*-
"""主要用来演示import的一些特性:多次重复使用import语句时，
不会重新加载被指定的模块，只是把对该模块的内存地址给引用到本地变量环境。"""
__author__ = 'dell'
import time

import used_module #第一次会打印used_module里面的语句
import os #再次导入os后，其内存地址和used_module里面的是一样的，因此这里只是对os的本地引用
print "os id in B:",id(os)
import used_module #第二次不会打印used_module里面的语句，因为没有重新加载