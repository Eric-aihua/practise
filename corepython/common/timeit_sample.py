# encoding=utf-8
__author__ = 'dell'
'''主要演示如何通过timeit来统计代码的执行时间'''

#被用来测试性能的函数
def for_sample():
    loop_count=10
    temp=0
    for i in range(loop_count):
        temp+=i;
    # print(temp)

#获取执行10万次for_sample所花费的时间
def compute_time():
    from timeit import Timer
    t1=Timer("for_sample()","from __main__ import for_sample");
    #打印执行10万次for_sample函数的时间
    print t1.timeit(100000)





compute_time()

