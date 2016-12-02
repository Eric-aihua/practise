# encoding:utf-8

"""该程序将消息发到MQ,Worker节点从MQ获取到消息后，执行对应的操作"""
from tasks import add
add.delay(dict(a='1',b='2'))
