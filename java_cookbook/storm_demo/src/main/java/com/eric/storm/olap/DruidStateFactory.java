package com.eric.storm.olap;

import org.apache.storm.task.IMetricsContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Eric on 2017/10/26.
 */
public class DruidStateFactory  implements StateFactory {
    private static final long serialVersionUID = 7019416924621892248L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
//    private  RealtimeNode rn=null;

    private static synchronized void startRealTime(){

    }

    @Override
    public State makeState(Map map, IMetricsContext iMetricsContext, int i, int i1) {
        return null;
    }
}
