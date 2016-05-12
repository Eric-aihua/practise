# -*- coding:utf-8 -*-
__author__ = 'eric.sun'
import sys
from pprint import pprint
from common import time_utils

def argv_sample():
    '''打印执行的参数，第一个是文件名'''
    print sys.argv
    #更好的输出结果
    print " ".join(sys.argv)

def exit_sample():
    try:
        #使用字符串作为退出说明
        sys.exit("exit by exit")
    except Exception ,e:
        print ">>>",e
    finally:
        print "finally block was called"

def modules_sample():
    '''打印模块名映射的实际模块路径'''
    print sys.modules["os.path"]
    print "ALL Modules:"
    pprint(sys.modules)


def platform_sample():
    print sys.platform

def input_sample():
    print sys.stdin
    #对stdout进行重定向，所有打印的内容都被写到tmp文件中
    sys.stdout=open("./tmp",'ab')
    print time_utils.get_format_timestamp()," write string into stdout and redirect into tmp file"


# argv_sample()
# exit_sample()
# modules_sample()
# platform_sample()
input_sample()