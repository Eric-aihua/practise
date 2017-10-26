package com.eric.storm.olap;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateUpdater;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric on 2017/10/26.
 */
public class DruidStateUpdater implements StateUpdater {
    @Override
    public void updateState(State state, List list, TridentCollector tridentCollector) {

    }

    @Override
    public void prepare(Map map, TridentOperationContext tridentOperationContext) {

    }

    @Override
    public void cleanup() {

    }
}
