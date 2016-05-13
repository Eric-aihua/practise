# -*- coding:utf-8 -*-
'''deque，全名double-ended queue）是一种具有队列和栈的性质的数据结构。
双端队列中的元素可以从两端弹出，其限定插入和删除操作在表的两端进行。'''
__author__ = 'eric.sun'

from collections import deque

q=deque(range(10))
q.append(5)
q.appendleft(6)
print q
print q.pop()
print q.popleft()
