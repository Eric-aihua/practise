package com.eric.storm.trident.windows.ewma;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eric on 2017/8/25.
 */
public class XMPPSenderBolt extends BaseFunction {
    private static final long serialVersionUID = -2764374850792954465L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String msg=(String)tridentTuple.get(0);
        logger.warn("##########################Log State Change:"+msg);
    }
}
