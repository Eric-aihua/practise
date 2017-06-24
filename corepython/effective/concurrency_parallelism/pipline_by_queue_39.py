# encoding:utf-8
__author__ = 'eric.sun'

from Queue import Queue
import threading

"""本代码主要演示如何基于Queue实现一个可靠的Queue.
   如果自定义Queue,可能又几个问题需要注意：
   1：为了判断任务是否结束，需要一个while循环监测队列
   2：处理完后，如何通知工作进程终止
   3：生产者的速率大于消费者时，会出现内存耗尽的情况
   4：多线程资源竞争的问题

"""


class ClosableQueue(Queue):
    # def __init__(self):
    #     super(ClosableQueue, self).__init__()
    SENTINEL = object()

    def close(self):
        self.put(self.SENTINEL)

    def __iter__(self):
        while True:
            item = self.get()
            try:
                if item is self.SENTINEL:
                    return
                yield item
            finally:
                self.task_done()


class StopableWorker(threading.Thread):
    def __init__(self, func, in_queue, out_queue):
        super(StopableWorker, self).__init__()
        self.func = func
        self.in_queue = in_queue
        self.out_queue = out_queue
        self.polled_count = 0
        self.worker_count = 0

    def run(self):
        for item in self.in_queue:
            result = self.func(item)
            self.out_queue.put(result)


def download(obj):
    print 'download:%s' % obj
    return obj


def resize(obj):
    print 'resize:%s' % obj
    return obj


def upload(obj):
    print 'upload:%s' % obj
    return obj


# 对于一个图片的处理流程 下载-->调整尺寸-->上传,通过多个线程的pipline来实现，线程之间的信息交互通过Queue完成
def process_picture_by_pipline():
    download_queue = ClosableQueue()
    resize_queue = ClosableQueue()
    upload_queue = ClosableQueue()
    done_queue = ClosableQueue()
    # done_queue=ClosableQueue()

    threads = [StopableWorker(download, download_queue, resize_queue),
               StopableWorker(resize, resize_queue, upload_queue),
               StopableWorker(upload, upload_queue, done_queue),
    ]
    for thread in threads:
        thread.start()

    for _ in range(1000):
        download_queue.put(object())

    download_queue.close()
    download_queue.join()

    resize_queue.close()
    resize_queue.join()

    upload_queue.close()
    upload_queue.join()
    # 通过线程安全的Queue,达到pipline处理完成的需求
    print 'done_queue size:' + str(done_queue.qsize())


if __name__ == '__main__':
    process_picture_by_pipline()



