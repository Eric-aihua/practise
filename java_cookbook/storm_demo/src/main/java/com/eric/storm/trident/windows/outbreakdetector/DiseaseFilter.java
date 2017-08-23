package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.operation.Filter;
import org.apache.storm.trident.operation.TridentOperationContext;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * 按照diagCode对Event进行过滤，只有大于322的Event才会继续处理
 */
public class DiseaseFilter extends BaseFilter {
    private static final long serialVersionUID = -424509656364513725L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean isKeep(TridentTuple tridentTuple) {
        DiagnosisEvent event=(DiagnosisEvent)tridentTuple.get(0);
        int diagCod=Integer.valueOf(event.getDiagCode());
        if (diagCod<=322){
            logger.info("Emitter diagCode["+event.getDiagCode()+"]");
            return true;
        }
        else{
            logger.info("Skip diagCode["+event.getDiagCode()+"]");
            return false;
        }
    }
}
