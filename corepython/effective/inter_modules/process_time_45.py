# encoding:utf-8
__author__ = 'eric.sun'

import time
import datetime

"""在需要进行时区转换的时间处理时，使用datetime取代time模块"""


def process_time_zone_by_time():
    now=time.time()
    print now
    time_tuple=time.localtime(now)
    time_format='%Y-%m-%d %H:%M:%S'
    # time tuple转换成固定格式的字符串
    time_str=time.strftime(time_format,time_tuple)
    print time_str
    # 将时间字符串按照格式转换成tuple
    time_tuple2=time.strptime(time_str,time_format)
    print time_tuple2
    # 将time tuple 转换成秒
    print time.mktime(time_tuple2)




def process_time_zone_by_datetime():
    pass


if __name__ == '__main__':
    process_time_zone_by_time()
    process_time_zone_by_datetime()