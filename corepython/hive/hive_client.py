# -*- coding:utf-8 -**
__author__ = 'eric.sun'
import sys
from hive_service import ThriftHive
from hive_service.ttypes import HiveServerException
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

'''
table_name_list:要删除表的列表
max_logtime:partition的最大值，小于该值得分区会被删除
'''
def clean_table_partitions(table_name_list,max_logtime):

    try:
        transport = TSocket.TSocket('100.5.24.137', 9991)
        transport = TTransport.TBufferedTransport(transport)
        protocol = TBinaryProtocol.TBinaryProtocol(transport)

        client = ThriftHive.Client(protocol)
        transport.open()
        for table in table_name_list:
            drop_part_ddl="ALTER TABLE " +table+" DROP PARTITION (log_time<'"+max_logtime+"')"
            print drop_part_ddl;
            client.execute(drop_part_ddl)
            print client.fetchAll()
        transport.close()
    except Thrift.TException, tx:
        print '%s' % (tx.message)
