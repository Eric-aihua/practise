# encoding:utf-8
import time
import random
import string

__author__ = 'aihua.sun'


import logging
from kafka import KafkaProducer
from kafka import KafkaClient


logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')
# hosts="native-lufanfeng-2-5-24-138:9092,native-lufanfeng-3-5-24-139:9092,native-lufanfeng-4-5-24-140:9092"
hosts="10.48.253.104:9092"
topic='spark_streaming_test_topic'

class KafkaSender():

    def __init__(self):
        self.client=KafkaClient(hosts)
        #self.producer = SimpleProducer(self.client,batch_send=batch_send,batch_send_every_n=batch_send_every_n)
        self.producer=KafkaProducer(bootstrap_servers=hosts)
        self.client.ensure_topic_exists(topic)
    def send_messages(self,msg):
        self.producer.send_messages(topic,msg)

def get_instance():
    return KafkaSender()

if __name__=="__main__":
    begin=time.time()
    producer=get_instance()
    for i in range(0,10000):
        msg='Message'+str(time.time)+' '+''.join(random.choice(string.lowercase) for i in range(64))+'\n'
        producer.send_messages(msg)
    end=time.time()
    print("use time:"+str((end-begin)))
