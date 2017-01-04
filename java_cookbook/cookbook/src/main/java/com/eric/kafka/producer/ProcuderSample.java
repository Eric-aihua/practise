package com.eric.kafka.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * Hello world!
 */
public class ProcuderSample {
	private final Producer<String, String> producer;
	public final static String TOPIC = "spark_streaming_test_topic";

	private ProcuderSample() {
		Properties props = new Properties();
		// 此处配置的是kafka的端口
		props.put("metadata.broker.list", "native-lufanfeng-2-5-24-138:9092,native-lufanfeng-3-5-24-139:9092,native-lufanfeng-4-5-24-140:9092");
		// 配置value的序列化类
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		// 配置key的序列化类
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");

		// request.required.acks
		// 0, which means that the producer never waits for an acknowledgement
		// from the broker (the same behavior as 0.7). This option provides the
		// lowest latency but the weakest durability guarantees (some data will
		// be lost when a server fails).
		// 1, which means that the producer gets an acknowledgement after the
		// leader replica has received the data. This option provides better
		// durability as the client waits until the server acknowledges the
		// request as successful (only messages that were written to the
		// now-dead leader but not yet replicated will be lost).
		// -1, which means that the producer gets an acknowledgement after all
		// in-sync replicas have received the data. This option provides the
		// best durability, we guarantee that no messages will be lost as long
		// as at least one in sync replica remains.
		props.put("request.required.acks", "-1");

		producer = new Producer<String, String>(new ProducerConfig(props));
	}

	public void deadLoopSendMessage(){
		int recordCount=0;
		List<KeyedMessage<String, String>> tmpList=new ArrayList<KeyedMessage<String, String>>();
		while(true){
			Random rand=new Random();
			// 批量发送数据
			String randResult="Index:"+recordCount+" Value:"+rand.nextInt(100)+"";
			tmpList.add(new KeyedMessage<String, String>(TOPIC, randResult , randResult));
			if (tmpList.size()%100==0){
				producer.send(tmpList);
				tmpList.clear();
			}
//			producer.send(new KeyedMessage<String, String>(TOPIC, randResult , randResult));
			recordCount+=1;
		}
	}



	public static void main(String[] args) {
		new ProcuderSample().deadLoopSendMessage();
	}
}
