package com.eric.storm.olap;

import org.apache.storm.kafka.trident.DefaultCoordinator;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * Created by Eric on 2017/10/26.
 */
public class FixEventSpout implements ITridentSpout {

    private static final long serialVersionUID = -7784045935347555872L;
    BatchCoordinator<Long> coordinator=new DefaultBatchCoordinator();
    Emitter<Long> emitter=new FixEventEmitter<Long>();

    @Override
    public BatchCoordinator getCoordinator(String s, Map map, TopologyContext topologyContext) {
        return coordinator;
    }

    @Override
    public Emitter getEmitter(String s, Map map, TopologyContext topologyContext) {
        return emitter;
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("message");
    }
}

class DefaultBatchCoordinator<Long> implements ITridentSpout.BatchCoordinator<Long> ,Serializable {
    private static final long serialVersionUID = 1677434212727545823L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public Long initializeTransaction(long l, Long aLong, Long x1) {
        logger.info("InitTransaction for txid:"+l);
        return null;
    }

    @Override
    public void success(long l) {
        logger.info("Successful for txid:"+l);
    }

    @Override
    public boolean isReady(long l) {
        return true;
    }

    @Override
    public void close() {

    }
}
