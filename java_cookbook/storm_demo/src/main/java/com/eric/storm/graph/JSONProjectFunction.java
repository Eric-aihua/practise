package com.eric.storm.graph;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;

/**
 * Created by Eric on 2017/8/29.
 */
public class JSONProjectFunction extends BaseFunction {
    public JSONProjectFunction(Fields jsonFields) {

    }

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {

    }


}
