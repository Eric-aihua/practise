# -*- coding:utf-8 -*-
from core.exception import module_a
import traceback

__author__ = 'eric.sun'

def catch_exception():
    try:
        module_a.method_aa()
    except BaseException ,e:
        print e.message
        traceback.print_exc("./a.text")

catch_exception()