# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming实现WordCount"""

from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark import SparkConf


TIME_UNIT=1

def start():
    sc=SparkContext(appName='NetworkWordCount')
    #一个时间单位是1
    sc.setCheckpointDir('/tmp/spark')
    ssc=StreamingContext(sc,TIME_UNIT)
    lines = ssc.socketTextStream("10.5.24.137", 9999)
    words = lines.flatMap(lambda line: line.split(" "))
    pairs = words.map(lambda word: (word, 1))
    # 窗口长度:3*TIME_UNIT 每次移动长度:2*TIME_UNIT
    wordCounts = pairs.reduceByKeyAndWindow(lambda x, y: x + y,3*TIME_UNIT,2*TIME_UNIT)
    print wordCounts
    wordCounts.pprint()
    ssc.start()             # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate

if __name__ == '__main__':
    start()
