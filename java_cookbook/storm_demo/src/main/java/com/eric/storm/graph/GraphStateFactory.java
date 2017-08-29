package com.eric.storm.graph;

import org.apache.storm.task.IMetricsContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateFactory;
import org.apache.storm.trident.state.StateSpec;

import java.util.Map;

/**
 * Created by Eric on 2017/8/29.
 */
public class GraphStateFactory implements StateFactory{
    private GraphFactory graphFactory;
    public GraphStateFactory(GraphFactory titanGraphFactory) {
        super();
        this.graphFactory=titanGraphFactory;
    }

    @Override
    public State makeState(Map map, IMetricsContext iMetricsContext, int i, int i1) {
        return null;
    }
}
