# -*- coding:utf-8 -**
from fabric.context_managers import cd
from fabric.decorators import roles
from fabric.tasks import execute

__author__ = 'sunaihua'

from fabric.api import local, env, run

"""
    fabfile默认文件名
"""

env.hosts = ['10.5.25.18', '10.5.24.137']
env.user = 'root'
env.key_filename = 'F:/Tools/key/sys_automation2.dat'

env.roledefs = {
    'master': ['10.5.24.137'],
    'slave': ['10.5.24.138', '10.5.24.139', '10.5.24.140']
}

env.key_filenames = {
    'master': 'F:/Tools/key/sys_automation2.dat',
    'slave': 'F:/Tools/key/sys_automation2.dat',
}


# 执行方式 fab hello
def hello():
    print 'hello world'


# 执行方式：fab hello_args:name=eric
def hello_args(name):
    print 'hello %s' % name


# 执行方式：fab exec_local_cmd
def exec_local_cmd():
    local('dir')


# 执行远程调用，使用env中定义的信息
def exec_remote_cmd():
    run('ls -l /root')


@roles('master')
def exec_master():
    with cd('/opt/PaaS/Talas'):
        run('ls -l')


@roles('slave')
def exec_slave():
    run('hostname')

# 使不同角色的远程主机执行不同的任务
def task():
    execute(exec_master)
    execute(exec_slave)
