package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.topology.TransactionAttempt;
import org.apache.storm.tuple.Fields;

import java.util.Map;

public class DiagnosisEventSpot implements ITridentSpout<Long> {
    private BatchCoordinator<Long> batchCoordinator = new DefaultBatchCoordinator<Long>();
    private Emitter<Long> emitter = new DiagnosisEmitter();

    @Override
    public BatchCoordinator getCoordinator(String s, Map map, TopologyContext topologyContext) {
        return batchCoordinator;
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
        return new Fields("spout");
    }

}

/**
 * 依靠元数据，进行批次重放
 */
class DiagnosisEmitter implements ITridentSpout.Emitter {
    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Object o, TridentCollector tridentCollector) {

    }

    @Override
    public void success(TransactionAttempt transactionAttempt) {

    }

    @Override
    public void close() {

    }
}


/**
 * 负责管理批次和元数据
 * @param <T>
 */
class DefaultBatchCoordinator<T> implements ITridentSpout.BatchCoordinator<Long> {
    @Override
    public Long initializeTransaction(long l, Long aLong, Long x1) {
        return null;
    }

    @Override
    public void success(long l) {

    }

    @Override
    public boolean isReady(long l) {
        return false;
    }

    @Override
    public void close() {

    }
}
