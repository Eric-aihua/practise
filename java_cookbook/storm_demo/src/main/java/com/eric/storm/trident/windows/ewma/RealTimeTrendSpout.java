package com.eric.storm.trident.windows.ewma;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.tuple.Fields;

import java.util.Map;

/**
 * 发送数据的Spout
 * Created by Eric on 2017/8/25.
 */
public class RealTimeTrendSpout implements ITridentSpout<Long> {
    private static final long serialVersionUID = 985445146696775065L;

    @Override
    public BatchCoordinator<Long> getCoordinator(String s, Map map, TopologyContext topologyContext) {
        return new BatchCoordinator<Long>() {
            @Override
            public Long initializeTransaction(long l, Long aLong, Long x1) {
                return null;
            }

            @Override
            public void success(long l) {

            }

            @Override
            public boolean isReady(long l) {
                return true;
            }

            @Override
            public void close() {

            }
        };
    }

    @Override
    public Emitter<Long> getEmitter(String s, Map map, TopologyContext topologyContext) {
        return new RealTImeTrendEmmitter();
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("RealTimeLog","LogTime");
    }
}
