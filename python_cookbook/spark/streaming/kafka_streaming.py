# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming 通过Kafka Streaming实现WordCount
   执行命令：./spark-submit --master spark://native-lufanfeng-1-5-24-137:7077 --packages org.apache.spark:spark-streaming-kafka_2.10:1.6.0 ../examples/kafka_streaming.py > log
   Kafka数据源程序：
"""

from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils


def start():
    sc=SparkContext(appName='KafkaWordCount')
    ssc=StreamingContext(sc,2)
    numStreams = 5
    kafkaStreams = [KafkaUtils.createStream(ssc,"native-lufanfeng-2-5-24-138:2181,native-lufanfeng-3-5-24-139:2181,native-lufanfeng-4-5-24-140:2181","streaming_test_group",{"spark_streaming_test_topic":1}) for _ in range (numStreams)]
    unifiedStream = ssc.union(*kafkaStreams)
    print unifiedStream
    #统计生成的随机数的分布情况
    result=unifiedStream.map(lambda x:(x[0],1)).reduceByKey(lambda x, y: x + y)
    result.pprint()
    ssc.start()             # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate

if __name__ == '__main__':
    start()
