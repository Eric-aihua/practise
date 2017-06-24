# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming通过netcat实现WordCount"""

from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark import SparkConf


def start():
    # local test mode
    # sc=SparkContext('local[2]',appName='NetworkWordCount')
    sc=SparkContext(appName='NetworkWordCount')
    ssc=StreamingContext(sc,1)
    # Create a DStream that will connect to hostname:port, like localhost:9999
    lines = ssc.socketTextStream("10.5.24.137", 9999)
    # Split each line into words
    words = lines.flatMap(lambda line: line.split(" "))
    # Count each word in each batch
    pairs = words.map(lambda word: (word, 1))
    wordCounts = pairs.reduceByKey(lambda x, y: x + y)
    print wordCounts
    # Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.pprint()
    ssc.start()             # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate

if __name__ == '__main__':
    start()
