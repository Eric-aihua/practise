# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming实现带有状态版本的WordCount"""

from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark import SparkConf


def start():
    sc = SparkContext(appName='NetworkWordCount')
    ssc = StreamingContext(sc, 1)
    #必须设置checkpoint地址
    sc.setCheckpointDir('/tmp')
    lines = ssc.socketTextStream("10.5.24.137", 9999)
    words = lines.flatMap(lambda line: line.split(" "))
    pairs = words.map(lambda word: (word, 1))

    def update_count(new_value, total_value):
        return sum(new_value, total_value or 0)
    #使用updateStateBykey针对输入的每个key进行有状态的统计（会一直累加每个key的count）
    total_count = pairs.updateStateByKey(updateFunc=update_count)
    total_count.pprint()
    ssc.start()  # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate


if __name__ == '__main__':
    start()
