# -*- coding:utf-8 -*-
__author__ = 'eric.sun'

import time

def get_format_timestamp():
    return time.strftime('%Y-%m-%d %H:%M:%S')

if __name__ == '__main__':
    print get_format_timestamp()

