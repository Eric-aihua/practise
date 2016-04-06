#  -*- coding: utf-8 -*-
__author__ = 'dell'
import os

#返回path规范化的绝对路径
print os.path.abspath("os_path.py")
#将path分割成目录和文件名二元组返回。
print os.path.split(os.path.abspath("os_path.py"))

