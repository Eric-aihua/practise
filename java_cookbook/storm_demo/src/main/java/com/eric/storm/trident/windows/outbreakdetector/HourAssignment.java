package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.Function;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * 通过Event计算出Hour的数据，并使用City+DiagCode+Hour组成Key
 */
public class HourAssignment extends BaseFunction {
    private static final long serialVersionUID = 8208049629164200860L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        DiagnosisEvent event=(DiagnosisEvent) tridentTuple.get(0);
        String city=(String)tridentTuple.get(1);
        long eventTimeStamp=event.getTime();
        long hour=eventTimeStamp/1000/60/60;
        String[] withCityHourKey={city+event.getDiagCode()+hour};
        tridentCollector.emit(Arrays.asList(withCityHourKey));
        logger.info("HourAssignment Result:"+withCityHourKey);
    }
}
