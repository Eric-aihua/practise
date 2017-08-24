package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.trident.state.map.IBackingMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通过ConcurrencyHashMap实现对状态的存储，也可以实现真正的持久化存储
 * Created by Eric on 2017/8/24.
 */
public class OutBreakTrendBackMapping implements IBackingMap<Long> {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private Map<String,Long> storage=new java.util.concurrent.ConcurrentHashMap();

    @Override
    public List<Long> multiGet(List<List<Object>> keys) {
        List<Long> result=new ArrayList<Long>();
        keys.forEach(key -> result.add(storage.get(key.get(0))!=null?storage.get(key.get(0)):new Long(0)));
        return result;
    }

    @Override
    public void multiPut(List<List<Object>> keys, List<Long> vals) {
        for(int i=0;i<keys.size();i++){
            String valueKey=(String)keys.get(i).get(0);
            Long value=vals.get(i);
            logger.info("----->Storage Key:"+valueKey+"  Value:"+value);
            storage.put(valueKey,value);
        }
    }
}
