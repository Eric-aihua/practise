# encoding:utf-8
__author__ = 'eric.sun'
import time
# 通过协成的方式实现并发



# 简单自定义协成
def simple_coroutine():
    while True:
        received = yield
        time.sleep(1)
        print 'Received Value:' + received

# 得到最小值的协成
def minimal_coroutine():
    # 第一个收到的数值作为初始值
    current = yield
    while True:
        value = yield current
        current = min(value, current)
        time.sleep(0.5)
        # print current


def simple_test():
    sc = simple_coroutine()
    # next的作用是将生成器推进到yield的位置
    next(sc)
    # send 过去的值，将会作为yield的返回结果
    start = time.time()
    sc.send('First')
    sc.send('Second')
    print 'elasted time:' + str(time.time() - start)

def minimal_test():
    start = time.time()
    mini=minimal_coroutine()
    next(mini)
    mini.send(10)
    print mini.send(3)
    print mini.send(1)
    print mini.send(-2)
    print mini.send(6)
    print 'elasted time:' + str(time.time() - start)



# if __name__ == '__main__':
#     # simple_test()
#     minimal_test()

def consumer():
    r = ''
    while True:
        n = yield r
        if not n:
            return
        print('[CONSUMER] Consuming %s...' % n)
        time.sleep(1)
        r = '200 OK'

def produce(c):
    c.next()
    n = 0
    while n < 5:
        n = n + 1
        print('[PRODUCER] Producing %s...' % n)
        r = c.send(n)
        print('[PRODUCER] Consumer return: %s' % r)
    c.close()

if __name__=='__main__':
    c = consumer()
    produce(c)