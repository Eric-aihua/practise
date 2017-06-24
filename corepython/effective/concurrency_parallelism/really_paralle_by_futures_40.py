# encoding:utf-8
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor

__author__ = 'eric.sun'

'''通过使用concurrent.features的相关工具实现真正的高并发'''
'''Python程序中对性能要求较高，且需要高并发的地方可以使用C来实现，但是需要增加大量的测试成本。
   Python自带的多线程机制，由于GIL的原因，并不能实现真正的并行计算，可以通过concurrent.features 执行多个子进程的方式来实现并行计算（开销较大）
'''

import time

numbers=[(1222351,1963309),(1222352,1963308),(1222355,1963301),(1223351,1963309),
         (1222350,1963306),(1222351,1963309),(1222351,1963309),(1522356,1923309),(1282354,1963306)]*10

# 最大公约数
def gcd(pair):
    a, b = pair
    low = min(a, b)
    for i in range(low, 0, -1):
        if a % i == 0 and b % i == 0:
            return i


def executor(func):
    start=time.time()
    func()
    print func.__name__+' 方法耗时:%s'%(time.time()-start)

def exec_by_seqence():
    for pair in numbers:
        gcd(pair)

#使用8个线程执行
def exec_by_threading_pool():
    thread_pool=ThreadPoolExecutor(max_workers=8)
    result=list(thread_pool.map(gcd,numbers))

#使用8个子进程执行
def exec_by_process_pool():
    """多个子进程真正的并行执行。代码执行逻辑为
    1：pickle对numbers进行序列化
    2: 通过本地套接字将序列化结果传给子进程
    3：子进程对传的数据反序列化，并引用包含gcd的py模块
    4：各个子进程并行执行gcd,并对结果序列化
    5:分别将结果传给父进程
    6：父进程对结果合并，并返回给调用者
    """
    thread_pool=ProcessPoolExecutor(max_workers=8)
    result=list(thread_pool.map(gcd,numbers))


'''执行结果:
exec_by_seqence 方法耗时:6.45399999619
exec_by_threading 方法耗时:9.47499990463
exec_by_process_pool 方法耗时:2.22800016403'''
if __name__ == '__main__':
    executor(exec_by_seqence)
    executor(exec_by_threading_pool)
    executor(exec_by_process_pool)
