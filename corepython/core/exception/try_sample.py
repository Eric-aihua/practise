# -*- coding:utf-8 -*-
__author__ = 'eric.sun'

def exec_finally():
    try:
        print 'exception'
        raise Exception("raise exception")
    except:
        print 'occur exception'
    finally:
        print 'exec finally code'

def exec_finally2():
    for i in range(2):
        try:
            print 'exception2'
            raise Exception("raise exception")
        except:
            print 'occur exception2'
            break
        finally:
            print 'exec finally code2'


#else只有在没有异常的时候才会执行
def try_else2():
    try:
        print 'exception'
    except:
        print 'occur exception'
    else:
        print 'all thing is ok'

def try_else():
    for i in range(2):
        try:
            print 'exception2'
            raise Exception("raise exception")
        except:
            print 'occur exception2'
            break
        else:
            print 'all thing is ok'

if __name__ == '__main__':
    try_else()
