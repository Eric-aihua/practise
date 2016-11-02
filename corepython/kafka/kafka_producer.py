# encoding:utf-8
import time

__author__ = 'aihua.sun'


import logging
import random,string
from kafka.producer import SimpleProducer
from kafka.client import KafkaClient


logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')
hosts={'native-lufanfeng-2-5-24-138':'9092','native-lufanfeng-3-5-24-139':'9092','native-lufanfeng-4-5-24-140':'9092'}
#hosts={'native-lufanfeng-2-5-24-138':'9092'}
topic='true_cloud_datapoint_topic2'

class TrueCloudDataPointProducer():

    def __init__(self,hosts,batch_send=True,batch_send_every_n=200,topic=""):
        self.hosts=hosts
        self.client=KafkaClient(self.hosts)
        self.batch_send=batch_send
        self.batch_send_every_n=batch_send_every_n
        self.producer = SimpleProducer(self.client,batch_send=batch_send,batch_send_every_n=batch_send_every_n)
        self.topic=topic
    def send_messages(self,msg):
        self.producer.send_messages(self.topic,msg)

def get_instance():
    return TrueCloudDataPointProducer(hosts,topic=topic)

if __name__=="__main__":
    begin=time.time()
    producer=get_instance()
    for i in range(0,10000):
    #while True:
        import time
        #msg='Message'+str(time.time)+' '+''.join(random.choice(string.lowercase) for i in range(64))+'\n'
        #msg='Message'+str(time.time)+'\n'
        messages=['Message'+str(time.time)+str(x)+'\n' for x in range(200)]
        import snappy
        import msgpack
        producer.send_messages(snappy.compress(msgpack.dumps(messages)))
        #time.sleep(1)
    end=time.time()
    print("use time:"+str((end-begin)))
