package com.eric.storm.graph;


import com.tinkerpop.blueprints.Graph;

import java.util.Map;

/**
 * Created by Eric on 2017/8/29.
 */
public interface GraphFactory {
    public Graph makeGraph(Map Conf);
}
