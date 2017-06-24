# encoding:utf-8
__author__ = 'eric.sun'
import threading
import time


"""演示如何使用Lock来防止资源竞争"""


class Counter(object):
    def __init__(self):
        self.count = 0

    def increment(self):
        self.count += 1


class LockingCounter(object):
    def __init__(self):
        self.count = 0
        #添加操作锁
        self.lock=threading.Lock()

    def increment(self):
        with self.lock:
            self.count += 1


def worker(count_times, counter):
    for _ in range(count_times):
        counter.increment()


# 没有使用Lock机制的情况下，使用多线程进程计数统计,由于increment不是原子性的，所以操作会被中断
def count_no_lock():
    single_thread_count_times = 10 ** 5
    thread_num = 5
    counter = Counter()
    start=time.time()
    threads = [threading.Thread(target=worker, args=(single_thread_count_times, counter)) for _ in range(thread_num)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    print 'No Locking Expect count:%s  result value:%s  elasted time:%s' % (thread_num * single_thread_count_times, counter.count,time.time()-start)


# 使用Lock机制的情况下，使用多线程进程计数统计,由于increment是原子性的，所以操作不会被中断.由于涉及到锁，所以效率会慢一点
def count_with_lock():
    single_thread_count_times = 10 ** 5
    thread_num = 5
    counter = LockingCounter()
    start=time.time()
    threads = [threading.Thread(target=worker, args=(single_thread_count_times, counter)) for _ in range(thread_num)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    print 'Has Locking:Expect count:%s  result value:%s  elasted time:%s' % (thread_num * single_thread_count_times, counter.count,time.time()-start)



if __name__ == '__main__':
    count_no_lock()
    count_with_lock()