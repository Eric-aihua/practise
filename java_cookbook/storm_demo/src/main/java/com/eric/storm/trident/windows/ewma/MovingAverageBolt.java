package com.eric.storm.trident.windows.ewma;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Eric on 2017/8/25.
 */
public class MovingAverageBolt extends BaseFunction {
    private static final long serialVersionUID = 4964034067055234526L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private EWMA ewma=null;
    private EWMA.Time emitRatePer=null;

    public MovingAverageBolt(EWMA ewma,EWMA.Time emitRatePer) {
        this.ewma = ewma;
        this.emitRatePer = emitRatePer;
    }

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        long logTime=(long)tridentTuple.get(1);
//        logger.info(">>>>>>EWMA Value:"+logTime);
        this.ewma.mark(logTime);
//        logger.debug("Rate: {}",this.ewma.getAverageRatePer(this.emitRatePer));
        tridentCollector.emit(new Values(this.ewma.getAverageRatePer(this.emitRatePer)));
    }
}
