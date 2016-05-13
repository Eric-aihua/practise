# -*- coding:utf-8 -*-
'''堆是一种优先队列，数据结构采用完全二叉树，父节点的优先级一定比子节点的优先级高，
最佳的使用场景是"添加新元素，删除最小元素"'''
__author__ = 'eric.sun'

from heapq import *
from random import shuffle
import time

data=range(10000000)
shuffle(data)

heap=[]
list_data=[]
[heappush(heap,element) for element in data]
[list_data.append(element) for element in data]

#比较查找最小元素的效率

#通过heap方法获取最小元素
start=time.time()
print heappop(heap)
end=time.time()
print "heap spend:",end-start

start=time.time()
#通过list方法获取最小元素
print min(list_data)
end=time.time()
print "list spend:",end-start

