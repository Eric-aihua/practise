# encoding:utf-8
import logging
from kafka.client import KafkaClient
from kafka.consumer import SimpleConsumer
import kafka_producer

__author__ = 'eric.sun'

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    # 如果配置filename就会输出到文件，否则就在console
                    # filename='myapp.log',
                    filemode='w')
import msgpack
import snappy


def decode(message):
    return msgpack.loads(snappy.decompress(message))

def get_message() :
    try :
        kconn = KafkaClient(kafka_producer.hosts , timeout = 10)
        getter = SimpleConsumer(kconn , 'test_group', kafka_producer.topic)
        #getter.seek(0, 0)
        while True:
            try:
                messages = getter.get_messages(200,timeout=3)
                if messages:
                    logging.info('get message from kafka done'+str(decode(messages)))
                import time
                time.sleep(0.1)
            except BaseException ,e:
                logging.error(str(e))
    except BaseException , e :
        logging.error(str(e) + 'get message from kafka failed')

get_message()
