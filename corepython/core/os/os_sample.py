#  -*- coding: utf-8 -*-
__author__ = 'dell'
import os
import sys
from pprint import pprint
print sys.modules['os']

def env_sample():
    pprint(os.environ)

def path_sample():
    #返回path规范化的绝对路径
    print os.path.abspath("os_sample.py")
    #将path分割成目录和文件名二元组返回。
    print os.path.split(os.path.abspath("os_sample.py"))


# path_sample()
env_sample()
