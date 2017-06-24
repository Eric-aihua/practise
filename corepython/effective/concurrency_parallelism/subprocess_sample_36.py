# encoding:utf-8
import subprocess
import time

__author__ = 'eric.sun'

"""主要演示使用subprocess执行子进程的方式"""

# 通过subprocess实现对echo命令的调用
def begin():
    sp = subprocess.Popen('echo helloworld', shell=True, stdout=subprocess.PIPE)
    result = sp.communicate()
    print result

# 演示如何监测子进程的状态(在linux上执行)
def check_subprocess_status():
    sp = subprocess.Popen("sleep 1", shell=True, stdout=subprocess.PIPE)
    while sp.poll() is None:
        print 'working...'
        time.sleep(0.1)

    print "退出状态:"+str(sp.poll())

# 多个子进程的并行执行
def multi_subprocess():
    def create_sub_process(period):
        sp=subprocess.Popen('sleep '+str(period),shell=True,stdout=subprocess.PIPE)
        return sp

    #每个子进程sleep 1秒,有十个子进程
    sub_process=[create_sub_process(1) for _ in range(10)]
    start=time.time()
    for sub_proces in sub_process:sub_proces.communicate()
    # 输出所有子进程的处理耗时,结果大概是1s多一点
    print time.time()-start

# 演示如何给subprocess传参数
# 数据->openssl加密(子进程)
# openssl 文件加密：openssl enc -des3 -in daily_report.sql -out ensql.sql -pass pass:'123456'
# openssl 文件解密：openssl enc -des3 -d -in ensql.sql -out result.sql -pass pass:'123456'
def run_ssl(ori_data):
    cmd="openssl enc -des3 -pass pass:'123456'"
    sp=subprocess.Popen(cmd,stdin=subprocess.PIPE,stdout=subprocess.PIPE)
    sp.stdin.write(ori_data)
    sp.stdin.flush()
    print sp.communicate()



if __name__ == '__main__':
    # begin()
    # check_subprocess_status()
    # multi_subprocess()
    run_ssl('sunaihua')