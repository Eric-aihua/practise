# -*- coding:utf-8 -*-
'''对已经加载的模块进行重新加载，一般用于原模块有变化等特殊情况，reload前该模块必须已经import过。'''
__author__ = 'dell'
import used_module#第一次import会打印used_module里面的语句
import time
print "used_module id in reload before:",id(used_module)
time.sleep(5)
reload(used_module)#如果在sleep的过程中used_module的代码被改了，则reload时新的代码会被执行，但是对象在内容地址不变
print "used_module id in reload after:",id(used_module)
