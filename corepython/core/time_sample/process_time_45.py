# encoding:utf-8
__author__ = 'eric.sun'

import time
import datetime

"""在需要进行时区转换的时间处理时，使用datetime取代time模块"""


def process_time_zone_by_time():
    # now=time.time()
    now=1504158000
    print now
    time_tuple=time.localtime(now)
    print time_tuple
    time_format='%Y-%m-%d %H:%M:%S'
    # time tuple转换成固定格式的字符串
    time_str=time.strftime(time_format,time_tuple)
    print time_str
    # 将时间字符串按照格式转换成tuple
    time_tuple2=time.strptime(time_str,time_format)
    print time_tuple2
    # 将time tuple 转换成秒
    print time.mktime(time_tuple2)

    # 字符串时间转时间搓
    datestr1 = '2015-06-06 10:10:10'
    print 'datestr to time :', time.mktime(time.strptime(datestr1, "%Y-%m-%d %H:%M:%S"))

    # 时间搓转格式化时间字符串
    time1 = time.time()
    print 'time to datestr :', time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(time1))

    # datetime对象转时间搓
    datetime1 = datetime.datetime.now()
    print 'datetime to time :', time.mktime(datetime1.timetuple())

    # 时间戳转datetime对象
    t1 = time.time()
    t2 = t1 + 20
    d1 = datetime.datetime.fromtimestamp(t1)
    d2 = datetime.datetime.fromtimestamp(t2)
    print 'time1 to datetime1 :', datetime.datetime.fromtimestamp(t1)
    print 'time2 to datetime2 :', datetime.datetime.fromtimestamp(t2)
    print 'seconds diff :', (d2 - d1).seconds




if __name__ == '__main__':
    process_time_zone_by_time()
