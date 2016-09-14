#-*- encoding:utf-8 -*-
# import xdbi.xhive as xhv

'''根据时间区间以及指定表创建分区'''
import time
from xdbi import xhv
__author__ = 'eric.sun'

def create_log_times(start_hours,end_hours):
    start_tuple_time=time.strptime(start_hours,'%Y%m%d%H')
    end_tuple_time=time.strptime(end_hours,'%Y%m%d%H')

    start_seconds=time.mktime(start_tuple_time)
    end_seconds=time.mktime(end_tuple_time)
    print start_seconds,end_seconds
    result=[start_hours]
    tmp_second=start_seconds
    while tmp_second<end_seconds:
        tmp_second=tmp_second+3600
        tmp_hours=time.strftime('%Y%m%d%H' , time.localtime(tmp_second))
        result.append(tmp_hours)

    # print result
    return result

def create_partitions():
    for table in tables:
        # sql="ALTER TABLE "+table+" ADD  "
        for pat in partitions:
            #sql=sql+"PARTITION (log_time='"+pat+"') location '/user/hive/warehouse/"+table+"/"+str(pat)+"'\n"
            sql="ALTER TABLE "+table+" ADD  "+"PARTITION (log_time='"+pat+"') location '/user/hive/warehouse/"+table+"/"+str(pat)+"'\n"
            print sql
            xhv.paas_query(sql)

tables=['host_daily_risk','host_vul_count','task_statistics','task_ip','scan_infos','scan_vuls']
#创建的分区包含end_hour
partitions=create_log_times('2015083110','2016080318')

create_partitions()
