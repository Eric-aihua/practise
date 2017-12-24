package com.eric.storm.olap;

import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Eric on 2017/10/26.
 */
public class MessageTypeFilter extends BaseFilter {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean isKeep(TridentTuple tridentTuple) {
//        Map<String,Object> dto=(Map<String,Object>)tridentTuple.get(0);
//        if ((int)dto.get("type")>3){
//            logger.info("########################Pass dto:"+dto);
//            return true;
//        }
//        return false;
        return true;
    }
}
