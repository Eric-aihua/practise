package com.eric.storm.graph;

import com.tinkerpop.blueprints.Graph;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 * Created by Eric on 2017/8/29.
 */
public interface GraphTupleProcessor {
    public void process(Graph graph, TridentTuple tuple, TridentCollector collector);
}
