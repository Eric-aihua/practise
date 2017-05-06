# encoding:utf-8
__author__ = 'eric.sun'

import hashlib

md5=hashlib.md5()
sha=hashlib.sha512()
src='i come from wuhan'
# 对原文进行hash处理
md5.update(src)
sha.update(src)
print md5.hexdigest()
print sha.hexdigest()