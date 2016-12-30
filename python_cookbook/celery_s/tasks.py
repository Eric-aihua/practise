# encoding:utf-8

"""运行在每个worker节点上的程序，
通过执行:celery -A tasks worker --loglevel=info  在节点上启动Worker 进程"""
import time
from celery import Celery

celery = Celery('tasks', broker='redis://10.5.24.5:6379/0')

@celery.task
def add(args):
    print('add result %s...' % args['a']+args['b'])
