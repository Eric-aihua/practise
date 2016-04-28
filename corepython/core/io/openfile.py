# -*- coding:utf-8 -*-

'''
#读写模式:r只读,r+读写,w新建(会覆盖原有文件),a追加,b二进制文件.常用模式

如:'rb','wb','r+b'等等

读写模式的类型有：

rU 或 Ua 以读方式打开, 同时提供通用换行符支持 (PEP 278)
w     以写方式打开，
a     以追加模式打开 (从 EOF 开始, 必要时创建新文件)
r+     以读写模式打开
w+     以读写模式打开 (参见 w )
a+     以读写模式打开 (参见 a )
rb     以二进制读模式打开
wb     以二进制写模式打开 (参见 w )
ab     以二进制追加模式打开 (参见 a )
rb+    以二进制读写模式打开 (参见 r+ )
wb+    以二进制读写模式打开 (参见 w+ )
ab+    以二进制读写模式打开 (参见 a+ )


注意：

1、使用'W'，文件若存在，首先要清空，然后（重新）创建，

2、使用'a'模式 ，把所有要写入文件的数据都追加到文件的末尾，即使你使用了seek（）指向文件的其他地方，如果文件不存在，将自动被创建。
'''
__author__ = 'eric.sun'

import time
import os

file_path="./test"+str(time.gmtime())
file=open(file_path,"a")

value="time:"+str(time.clock())+"\n"

#比较write_file_andif与write_file_only方法的效率比
def write_effective():
    from timeit import Timer
    print "start to test"
    #t1=Timer("write_file_only()",setup="from __main__ import write_file_only");
    t1=Timer("write_file_andif()",setup="from __main__ import write_file_andif");
    #打印执行XX次函数的时间
    print t1.timeit(100000)

#只写文件
def write_file_andif():
    if not os.path.exists(file_path):
        pass
    file.write(value)
    file.flush()

#只写文件
def write_file_only():
    file.write(value)
    file.flush()


#简单的写文件
def write_file():
    file=open("./test","ab")
    # value="time:"+str(time.clock())+"\n"
    # # print value
    # file.write(value)
    # file.flush()
    # time.sleep(100)
    file.close()


if __name__ == '__main__':
    write_file()