# -*- coding:utf-8 -*-
__author__ = 'eric.sun'

import time
import os.path

TIME_FORMAT = '%Y-%m-%d %H:%M:%S'

# 获取指定格式的时间
def get_format_timestamp():
    return time.strftime('%Y-%m-%d %H:%M:%S')

def cmp_time():
    test_time=time.strptime('2016-12-01 11:24:35',TIME_FORMAT)
    begin_time = time.strptime('2016-12-01 11:24:32', TIME_FORMAT)
    end_time = time.strptime('2016-12-01 11:24:38', TIME_FORMAT)
    print test_time.__lt__(end_time)
    print test_time.__gt__(begin_time)

if __name__ == '__main__':
    print get_format_timestamp()
    cmp_time()

