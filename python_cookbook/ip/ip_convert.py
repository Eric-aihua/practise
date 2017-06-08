# -*- coding:utf-8 -**
from netaddr import *

__author__ = 'sunaihua'

print '#'*10,'IPNetwork'
network=IPNetwork('192.0.2.5/24')
network3=IPNetwork('192.0.2.6/24')
network2=IPNetwork('192.0.1.5')

print network
print network.broadcast
print network.netmask
print network.cidr  #IP段的CIDR形式
print network.netmask  #IP段的网络掩码
print network.first  #IP段的起始IP地址——以整数形式表示
print network.last  #IP段的结束IP地址——以整数形式表示
print network.size  #IP段的大小(包含多少个IP地址)
print network.value  #IP地址——以整数形式表示
print network.ip  #IP地址
print network.network  #IP所在网段
print '#'*10,'IPRange'
print network3
print network2
print network.__contains__(network3) #判断是否属于一个network
print network.__contains__(network2)#判断是否属于一个network

iprange=IPRange(network.first, network.last)
print iprange
print iprange.cidrs()[0] #转换成CIDR







