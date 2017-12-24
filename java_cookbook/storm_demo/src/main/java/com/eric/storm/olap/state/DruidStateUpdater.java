package com.eric.storm.olap.state;

import com.eric.storm.olap.FixMessageDto;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.state.StateUpdater;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric on 2017/12/20.
 */
public class DruidStateUpdater implements StateUpdater<DruidState> {
    @Override
    public void updateState(DruidState druidState, List<TridentTuple> list, TridentCollector tridentCollector) {
        for (TridentTuple tuple : list) {
            Map<String, Object> message = (Map<String, Object>) tuple.getValue(0);
            druidState.aggregateMessage(message);
        }
    }

    @Override
    public void prepare(Map map, TridentOperationContext tridentOperationContext) {

    }

    @Override
    public void cleanup() {

    }
}
