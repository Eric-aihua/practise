#-*- coding:utf-8 -*-
import re

__author__ = 'eric.sun'

def exp_sample():
    pattern=re.compile(".*word");
    match1=pattern.match('hello')
    match2=pattern.match('hello word')
    print match1
    print match2

    #不用compile函数
    m = re.match(r'hello', 'hello world!')
    print m

    #不匹配
    print re.match(r'.*word','hello world!')
    #匹配
    print re.match(r'.*word*','hello world!').group()
    #匹配
    print re.match(r'.*','hello world!').group()




exp_sample()