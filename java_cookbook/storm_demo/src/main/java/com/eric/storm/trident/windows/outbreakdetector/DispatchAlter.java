package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.Function;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DispatchAlter extends BaseFunction {
    private static final long serialVersionUID = -6619782418545145108L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String alterMsg=(String)tridentTuple.get(0);
        logger.error("###########################Found OutBreak:"+alterMsg);
        System.exit(0);
    }

}
