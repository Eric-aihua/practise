# encoding:utf-8
__author__ = 'eric.sun'
'''主要演示如何使用迭代器作为参数传递'''

# 公共处理逻辑
def nomalize(customers):
    total = sum(customers)
    result = []
    for item in customers:
        result.append(100 * item / total)
    print result


# 公共处理逻辑的copy版本
def nomalize_copy(customers):
    customers=list(customers)
    total = sum(customers)
    result = []
    for item in customers:
        result.append(100 * item / total)
    print result

# 数据量小的时候采取的方式
def gen_customers_normal():
    return range(10)


# 数据量大时，使用生成器产生数据
def gen_customers_miss_data():
    for item in range(10):
        yield item


# 创建一个包含生成器功能的容器，来解决传输大的结果集以及多次读取的问题
class BigParameterVist(object):
    #通过实现__iter__方法实现一个容器
    def __iter__(self):
        for item in range(10):
            yield item

if __name__ == '__main__':
    # 数据量小的时候采取的方式
    nomalize(gen_customers_normal())
    # print type(gen_customers_miss_data())
    # print dir(gen_customers_miss_data())

    # 如果需要迭代的数据很大，采用生成器的方式返回结果，但是由于生成器只能产生一次结果，所以最后的结果为空
    nomalize(gen_customers_miss_data())

    # 通过在nomalize中转成list解决数据丢失的问题，但是如果数据量很大，有可能在生成list的过程中内存溢出
    nomalize_copy(gen_customers_miss_data())

    # 通过遍历包含生成器的容器，解决上面所有的问题
    visit=BigParameterVist()
    nomalize(visit)