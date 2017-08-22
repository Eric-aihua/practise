package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.Function;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.Map;

public class CityAssignment implements Function {
    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {

    }

    @Override
    public void prepare(Map map, TridentOperationContext tridentOperationContext) {

    }

    @Override
    public void cleanup() {

    }
}
