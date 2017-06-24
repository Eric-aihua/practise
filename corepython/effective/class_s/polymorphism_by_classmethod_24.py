# encoding:utf-8

import os
import threading


__author__ = 'eric.sun'

"""主要演示如何使用classmethod来达到多态的效果"""

# 读取文件接口类，子类需要实现read
class InputData():
    def read(self):
        raise NotImplementedError()


# 子类，实现read方法
class PathInputData(InputData):
    def __init__(self, path):
        self.path = path

    def read(self):
        return open(self.path).read()


# MapReduce的接口类，定义MapReduce方法
class Worker():
    def __init__(self, input_impl):
        self.input_data = input_impl.read()
        self.result = None

    def map(self):
        raise NotImplementedError

    def reduce(self, other):
        raise NotImplementedError


# MapReduce的实现类，实现对文件行数的统计
class LineCountWorker(Worker):
    def map(self):
        self.result = self.input_data.count('\n')

    def reduce(self, other):
        self.result += other.result


def generate_inputs(data_dir):
    for fn in os.listdir(data_dir):
        if os.path.isfile(os.path.join(data_dir, fn)):
            # print os.path.join(data_dir, fn)
            yield PathInputData(os.path.join(data_dir, fn))


def create_works(input_list):
    for input_impl in input_list:
        yield LineCountWorker(input_impl)


def execute(works):
    tmp_works = list(works)
    threads = [threading.Thread(target=worker.map) for worker in tmp_works]
    for thread in threads: thread.start()
    for thread in threads: thread.join()
    first_worker, other_workers = tmp_works[0], tmp_works[1:]
    for other_worker in other_workers:
        first_worker.reduce(other_worker)

    print first_worker.result

# 通用读取接口类，子类需要实现read,以及gen_inputs方法
class GenericInputData():
    def read(self):
        raise NotImplementedError()

    @classmethod
    def gen_inputs(cls,config):
        data_dir=config['datadir']
        for fn in os.listdir(data_dir):
            if os.path.isfile(os.path.join(data_dir, fn)):
                yield cls(os.path.join(data_dir, fn))

# 子类，实现read,gen_inputs方法
class GenericPathInputData(GenericInputData):
    def __init__(self, path):
        self.path = path

    def read(self):
        return open(self.path).read()



# 通用MapReduce的接口类，定义MapReduce方法
class GenericWorker():
    def __init__(self, input_impl):
        self.input_data = input_impl.read()
        self.result = None

    def map(self):
        raise NotImplementedError

    def reduce(self, other):
        raise NotImplementedError

    @classmethod
    def create_works(cls,input_list):
        for input_impl in input_list:
            yield cls(input_impl)


# MapReduce的实现类，实现对文件行数的统计
class GenericLineCountWorker(GenericWorker):
    def map(self):
        self.result = self.input_data.count('\n')

    def reduce(self, other):
        self.result += other.result




def mapreduce_by_normal(dir):
    """
    使用普通的方式实现MapReduce操作，但是由于在generate_inputs，create_works中写的都是子类名称，所以当添加新的实现子类时，
    需要修改这两个方法的代码,甚至mapreduce_by_normal方法的参数都需要调整
    """
    inputs = generate_inputs(dir)
    workers = create_works(inputs)
    execute(workers)


def mapreduce_by_clsmethod(input_cls,worker_cls,config):
    """
    通过传入cls的方式，实现类似多态的机制，从而减少耦合
    在增加新的实现类时，对现有代码没有影响
    """
    input_list=input_cls.gen_inputs(config)
    workers=worker_cls.create_works(input_list)
    execute(workers)

if __name__ == '__main__':
    mapreduce_by_normal("E:\Sourcecode\github\practise\corepython\core\magic_method")

    config={'datadir':"E:\Sourcecode\github\practise\corepython\core\magic_method"}
    mapreduce_by_clsmethod(GenericPathInputData,GenericLineCountWorker,config)