# encoding:utf-8
__author__ = 'eric.sun'
"""演示如何正确的使用默认的动态参数"""
import time
import json


def incorrect_log(msg, log_time=time.time()):
    """
    本函数的初衷是想log_time的默认值为当前时间，不同时间调用该方法时，log_time的值是不同的
    但是由于方法的默认参数只会在模块加载时初始化一次，所以不管掉多少次log_time的值都是相同的
    :param msg:
    :param log_time:
    """
    print msg, log_time


def correct_log(msg, log_time=None):
    """
    通过在参数定义时，默认值使用None,代码中使用的时候进行判断的方式解决incorrect_log中的问题
    :param msg:
    :param log_time:
    """
    log_time = log_time if log_time else time.time()
    print msg, log_time


def incorrect_decode(json_str, default={}):
    """
    初衷是json_str在decode失败时，返回空的map,但是本方法会返回同一个map对象
    :param json_str:
    :param default:
    """
    try:
        json.load(json_str)
    except:
        return default


def correct_decode(json_str, default=None):
    """
    正确的调用方法，在default为None时，重新返回一个{}
    :param json_str:
    :param default:
    """
    default = default if default else {}
    try:
        json.load(json_str)
    except:
        return default


def exec_incorrect_log():
    incorrect_log('first log')
    time.sleep(1)
    incorrect_log('second log')


def exec_correct_log():
    correct_log('first log')
    time.sleep(1)
    correct_log('second log')


def exec_incorrect_json_decode():
    first_result = incorrect_decode('firstK')
    first_result['k1'] = 'v1'
    second_result = incorrect_decode('secondK')
    second_result['k2'] = 'v2'
    print first_result
    print second_result
    assert first_result is second_result


def exec_correct_json_decode():
    first_result = correct_decode('firstK')
    first_result['k1'] = 'v1'
    second_result = correct_decode('secondK')
    second_result['k2'] = 'v2'
    print first_result
    print second_result
    assert first_result is not second_result


if __name__ == '__main__':
    # 调用错误的方法
    # exec_incorrect_log()
    # 调用正确的方法
    # exec_correct_log()

    # 调用错误的方法
    # exec_incorrect_json_decode()
    # 调用正确的方法
    exec_correct_json_decode()