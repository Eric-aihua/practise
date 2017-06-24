# encoding:utf-8
__author__ = 'eric.sun'

"""演示如何使用Spark Streaming 通过Kafka Direct Streaming实现WordCount
   执行命令：./spark-submit --master spark://native-lufanfeng-1-5-24-137:7077 --packages org.apache.spark:spark-streaming-kafka_2.10:1.6.0 ../examples/kafka_streaming.py > log
   Kafka数据源程序：https://github.com/Eric-aihua/practise.git/java_cookbook/cookbook/src/main/java/com/eric/kafka/producer/ProcuderSample.java

   使用Direct的好处
   1：根据topic的分区数默认的创建对应数量的rdd分区数
   2：Receiver的方式需要通过Write AHead Log的方式确保数据不丢失，Direct的方式不需要
   3：一次处理：使用Kafka Simple API对数据进行读取，使用checkpoint对offset进行记录

   问题：
   基于Zookeeper的Kafka监控工具不能获取offset的值了，需要在每次Batch处理后，可以对Zookeeper的值进行设置

"""

from pyspark import SparkContext
from pyspark import SparkConf
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils


def start():
    sconf=SparkConf()
    sconf.set('spark.cores.max' , 8)
    sc=SparkContext(appName='KafkaDirectWordCount',conf=sconf)
    ssc=StreamingContext(sc,2)

    brokers="native-lufanfeng-2-5-24-138:9092,native-lufanfeng-3-5-24-139:9092,native-lufanfeng-4-5-24-140:9092"
    topic='spark_streaming_test_topic'
    kafkaStreams = KafkaUtils.createDirectStream(ssc,[topic],kafkaParams={"metadata.broker.list": brokers})
    #统计生成的随机数的分布情况
    result=kafkaStreams.map(lambda x:(x[0],1)).reduceByKey(lambda x, y: x + y)
    #打印offset的情况，此处也可以写到Zookeeper中
    #You can use transform() instead of foreachRDD() as your
    # first method call in order to access offsets, then call further Spark methods.
    kafkaStreams.transform(storeOffsetRanges).foreachRDD(printOffsetRanges)
    result.pprint()
    ssc.start()             # Start the computation
    ssc.awaitTermination()  # Wait for the computation to terminate

offsetRanges = []

def storeOffsetRanges(rdd):
    global offsetRanges
    offsetRanges = rdd.offsetRanges()
    return rdd

def printOffsetRanges(rdd):
    for o in offsetRanges:
        print "%s %s %s %s %s" % (o.topic, o.partition, o.fromOffset, o.untilOffset,o.untilOffset-o.fromOffset)

if __name__ == '__main__':
    start()
