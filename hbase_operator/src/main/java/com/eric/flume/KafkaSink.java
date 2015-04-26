package com.eric.flume;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventHelper;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

/**
 * kafka sink.
 */
public class KafkaSink extends AbstractSink implements Configurable {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSink.class);
	private Properties parameters;
	private Producer<String, String> producer;
	private Context context;

	public void configure(Context context) {
		this.context = context;
		ImmutableMap<String, String> props = context.getParameters();
		parameters = new Properties();
		for (String key : props.keySet()) {
			String value = props.get(key);
			this.parameters.put(key, value);
		}
	}

	public synchronized void start() {
		super.start();
		ProducerConfig config = new ProducerConfig(this.parameters);
		this.producer = new Producer<String, String>(config);
	}

	public Status process() throws EventDeliveryException {
		Status status = null;
		// Start transaction
		Channel channel = getChannel();
		Transaction txn = channel.getTransaction();
		txn.begin();
		try {
			// This try clause includes whatever Channel operations you want to
			// do
			Event event = channel.take();
			String partitionKey = (String) parameters.get("custom.partition.key");
			String encoding = StringUtils.defaultIfEmpty((String) this.parameters.get("custom.encoding"), "UTF-8");
			String topic = StringUtils.defaultIfEmpty((String) this.parameters.get("custom.topic"), "flume_sink");
			String eventData = new String(event.getBody(), encoding);
			KeyedMessage<String, String> data;
			// if partition key does'nt exist
			if (StringUtils.isEmpty(partitionKey)) {
				data = new KeyedMessage<String, String>(topic, eventData);
			} else {
				data = new KeyedMessage<String, String>(topic, partitionKey, eventData);
			}
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Send Message to Kafka : [" + eventData + "] -- [" + EventHelper.dumpEvent(event) + "]");
			}
			producer.send(data);
			txn.commit();
			status = Status.READY;
		} catch (Throwable t) {
			txn.rollback();
			status = Status.BACKOFF;
			// re-throw all Errors
			if (t instanceof Error) {
				throw (Error) t;
			}
		} finally {
			txn.close();
		}
		return status;
	}

	/**
	 * Stop void.
	 */
	@Override
	public void stop() {
		producer.close();
	}
}