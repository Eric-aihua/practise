# encoding:utf-8
import time
from datetime import datetime
import pytz

"""使用time模块对本地时间进行处理是依赖c函数与操作系统的交互进行的，会不稳定，涉及到时区的转换时，最好使用datetime"""

# 进行简单的时间操作
def process_time_by_time():
    now=time.time()
    print now
    time_format='%Y-%m-%d %H:%M:%S'
    time_tuple=time.localtime(now)
    # convert utc to time str
    time_str=time.strftime(time_format,time_tuple)
    print time_str
    # convert time_str to utc
    utc_time=time.mktime(time.strptime(time_str,time_format))
    print utc_time


# 使用time模块对timezone进行处理
def process_timezone_by_time():
    now = time.time()
    print now
    time_format = '%Y-%m-%d %H:%M:%S %Z'
    # convert utc to time str
    time_str = '2017-02-18 10:38:28 CST'
    time_str2 = '2017-02-18 10:38:28 PDT'
    # convert time_str to utc
    utc_time = time.strftime(time_format,time.strptime(time_str, time_format))
    # 不能正确解析PDT时区
    #utc_time2 = time.strftime(time_format,time.strptime(time_str2, time_format))
    print utc_time
    #print utc_time2

# 使用datetime对timezone进行处理
def process_timezone_by_datetime():
    time_format = '%Y-%m-%d %H:%M:%S'
    time_str=time.strftime(time_format,time.localtime(time.time()))
    nyc_dt_naive=datetime.strptime(time_str,time_format)
    shanghai=pytz.timezone('Asia/Shanghai')
    nyc_dt=shanghai.localize(nyc_dt_naive)
    print 'Shanghai Time:%s' %time_str
    # 1,先将本地时间转换为UTC时间
    utc_dt=pytz.utc.normalize(nyc_dt.astimezone(pytz.utc))
    print 'UTC Time:%s' %utc_dt   
    # 2,将utc时间转换为pacifi时间 
    pacific=pytz.timezone('US/Pacific')
    pac_dt=pacific.normalize(utc_dt.astimezone(pacific))
    print 'Pacific Time:%s' %(pac_dt)

    # 3,将utc时间转换为首尔时间 
    seoul=pytz.timezone('Asia/Seoul')
    seoul_dt=seoul.normalize(utc_dt.astimezone(seoul))
    print 'Seoul Time:%s' %(seoul_dt)



if __name__ == '__main__':
    #process_time_by_time()
    #process_timezone_by_time()
    process_timezone_by_datetime()
