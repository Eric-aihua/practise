# -*- coding:utf-8 -*-
'''同import语句同样的功能，但__import__是一个函数，并且只接收字符串作为参数，所以它的作用就可想而知了。
其实import语句就是调用这个函数进行导入工作的，import sys <==>sys = __import__('sys')

通常在动态加载时可以使用到这个函数，比如你希望加载某个文件夹下的所用模块，
但是其下的模块名称又会经常变化时，就可以使用这个函数动态加载所有模块了，最常见的场景就是插件功能的支持。
'''
__author__ = 'eric.sun'
__import__("used_module")
__import__("used_module")#不会打印信息
import sys
del sys.modules["used_module"]

__import__("used_module")#再次导入还是会打印消息，因为已经unimport一次了
