package com.eric.storm.graph;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.state.BaseStateUpdater;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.List;

/**
 * Created by Eric on 2017/8/29.
 */
public class GraphUpdater extends BaseStateUpdater<GraphState> {
    public GraphUpdater(TwitterGraphTupleProcessor twitterGraphTupleProcessor) {
    }

    @Override
    public void updateState(GraphState graphState, List<TridentTuple> list, TridentCollector tridentCollector) {

    }
}
