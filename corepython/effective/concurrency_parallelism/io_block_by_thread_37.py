# encoding:utf-8
__author__ = 'eric.sun'

import threading
import time
import select

"""主要用来演示多线程的特性"""

shared_object = [2139079, 2121079, 2134479, 12166079, 2122079, 32121079]
SYSTEM_CALL_TIMES = 3


def factorize(number):
    for i in range(1, number + 1):
        if number % i == 0:
            yield i


# 模拟一个需要耗时1秒的系统调用,下面的两种方式都可以
def slowly_system_call():
    # select 需要在linux上执行
    #select.select([], [], [], 1)
    time.sleep(1)


def serial_system_call():
    start = time.time()
    for i in range(SYSTEM_CALL_TIMES): slowly_system_call()
    print '耗时：' + str(time.time() - start)


def serial_run():
    start = time.time()
    for num in shared_object:
        list(factorize(num))
    print '耗时：' + str(time.time() - start)


# 由于Python采用GIL来对多个线程进行协调，而GIL的本质是全局锁，
# 所以通过python threading执行多线程的方式，实际还是一个一个线程在执行，而且消耗的时间有可能比单个线程还要慢
def paralle_run():
    start = time.time()
    # 每个操作耗时1秒，启动四个线程，理论上最终执行时间为1秒多一点，但是实际的结果是4秒
    class FactorizeThread(threading.Thread):
        def __init__(self, number):
            super(FactorizeThread, self).__init__()
            self.number = number

        def run(self):
            self.factors = list(factorize(self.number))

    threads = []
    for number in shared_object:
        thread = FactorizeThread(number)
        thread.start()
        threads.append(thread)
    for thread in threads:
        thread.join()

    print '耗时：' + str(time.time() - start)


# GIL在 系统调用，阻塞I/O操作时，会释放对应的全局锁，所以会达到平行执行的效果
def paralle_system_call():
    start = time.time()
    threads = [threading.Thread(target=slowly_system_call) for _ in range(SYSTEM_CALL_TIMES)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    print '耗时：' + str(time.time() - start)


# 对比串行执行与并发执行计算损耗的时间
def compare_effective_for_compute():
    serial_run()
    paralle_run()


# 对比串行执行与并发执行系统调用损耗的时间
def compare_effective_for_systemcall():
    serial_system_call()
    paralle_system_call()


if __name__ == '__main__':
    # compare_effective_for_compute()
    compare_effective_for_systemcall()
