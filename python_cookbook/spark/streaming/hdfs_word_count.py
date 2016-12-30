# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming实现WordCount"""

from pyspark import SparkContext
from pyspark.streaming import StreamingContext


def start():
    sc=SparkContext(appName='HdfsWordCount')
    ssc=StreamingContext(sc,5)
    #只会统计从启动streaming开始，新创建的文件
    lines = ssc.textFileStream('/user/hive/warehouse/streaming_status/2016091101')
    words = lines.flatMap(lambda line: line.split(" "))
    pairs = words.map(lambda word: (word, 1))
    wordCounts = pairs.reduceByKey(lambda x, y: x + y)
    wordCounts.pprint()
    ssc.start()             # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate

if __name__ == '__main__':
    start()
