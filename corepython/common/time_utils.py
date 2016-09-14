# -*- coding:utf-8 -*-
__author__ = 'eric.sun'

import time
import os.path

def get_format_timestamp():
    os.path.getmtime()
    return time.strftime('%Y-%m-%d %H:%M:%S')

if __name__ == '__main__':
    print get_format_timestamp()

