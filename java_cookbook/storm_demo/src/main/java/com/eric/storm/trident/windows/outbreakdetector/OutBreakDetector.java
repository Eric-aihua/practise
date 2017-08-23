package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.Function;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.Map;

public class OutBreakDetector extends BaseFunction {
    private static final long serialVersionUID = 3578846937178639008L;

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {

    }
}
