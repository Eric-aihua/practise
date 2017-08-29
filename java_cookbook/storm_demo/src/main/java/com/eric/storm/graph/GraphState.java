package com.eric.storm.graph;


import com.tinkerpop.blueprints.Graph;
import org.apache.storm.trident.state.State;

/**
 * Created by Eric on 2017/8/29.
 */
public class GraphState implements State {

    private Graph graph;
    private GraphState(Graph graph){
        this.graph=graph;
    }

    @Override
    public void beginCommit(Long aLong) {

    }

    @Override
    public void commit(Long aLong) {

    }
}
