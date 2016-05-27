# -*- coding:utf-8 -*-
from core.exception import module_a
import traceback
import logging

__author__ = 'eric.sun'

def catch_exception():
    try:
        module_a.method_aa()
    except BaseException ,e:
        logging.error("Error:%s" %(e.message))
        traceback.print_exc()
        logging.error("######################")

        #traceback.print_exc("./a.text")


def raise_exception():
    raise Exception("raise a exception for test %s" %(3))

catch_exception()
#raise_exception()