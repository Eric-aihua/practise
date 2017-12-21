package com.eric.storm.olap.state;

import com.eric.storm.olap.DruidTranquilitySender;
import org.apache.storm.Config;
import org.apache.storm.task.IMetricsContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateFactory;

import java.util.Map;

/**
 * Created by Eric on 2017/12/20.
 */
public class DruidStateFactory implements StateFactory {
    private static final long serialVersionUID = 2456428262362210245L;
    private static final DruidTranquilitySender sender = new DruidTranquilitySender("example.json", "stat_sip");

    public DruidStateFactory(Config config) {

    }


    @Override
    public State makeState(Map map, IMetricsContext iMetricsContext, int partitionIndex, int numPartitions){
        return new DruidState(sender,partitionIndex);
    }
}
