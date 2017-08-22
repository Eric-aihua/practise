package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.Filter;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.Map;

public class DiseaseFilter implements Filter {
    @Override
    public boolean isKeep(TridentTuple tridentTuple) {
        return false;
    }

    @Override
    public void prepare(Map map, TridentOperationContext tridentOperationContext) {

    }

    @Override
    public void cleanup() {

    }
}
