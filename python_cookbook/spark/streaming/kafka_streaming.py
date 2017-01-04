# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming 通过HDFS实现WordCount"""

from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils


def start():
    sc=SparkContext(appName='HdfsWordCount')
    ssc=StreamingContext(sc,2)
    numStreams = 5
    kafkaStreams = [KafkaUtils.createStream(ssc,"native-lufanfeng-2-5-24-138:2181,native-lufanfeng-3-5-24-139:2181,native-lufanfeng-4-5-24-140:2181","streaming_test_group",{"spark_streaming_test_topic":1}) for _ in range (numStreams)]
    unifiedStream = ssc.union(*kafkaStreams)
    random_nums=unifiedStream.map(lambda s:s.split(':')[2])
    result=random_nums.reduce(lambda x,y:x+y)
    print result
    ssc.start()             # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate

if __name__ == '__main__':
    start()
