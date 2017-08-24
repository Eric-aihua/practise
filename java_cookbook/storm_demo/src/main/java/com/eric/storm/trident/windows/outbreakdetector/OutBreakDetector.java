package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.Arrays;

/**
 * 当发现疾病的数量超过阀值时，产生一条告警
 */
public class OutBreakDetector extends BaseFunction {
    private static final long serialVersionUID = 3578846937178639008L;
    private  static final long THRESHOLD=10000;
    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String outBreakWithCityHourKey=(String)tridentTuple.get(0);
        Long count=(Long)tridentTuple.get(1);
        if (count>THRESHOLD){
            String alterMsg="Key:"+outBreakWithCityHourKey+" Count is:"+count;
            tridentCollector.emit(Arrays.asList(alterMsg));
        }
    }
}
