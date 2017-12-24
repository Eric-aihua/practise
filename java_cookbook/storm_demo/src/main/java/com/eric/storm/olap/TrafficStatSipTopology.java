package com.eric.storm.olap;

import com.eric.storm.olap.state.DruidStateFactory;
import com.eric.storm.olap.state.DruidStateUpdater;
import org.apache.storm.Config;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

/**
 * 用来分析流量数据中源IP的统计数据
 * Created by Eric on 2017/8/29.
 */
public class TrafficStatSipTopology {
    public static StormTopology buildTopology(Config config){
        TridentTopology topology = new TridentTopology();
        TrafficStatSipSpout spout = new TrafficStatSipSpout();
        Stream inputStream = topology.newStream("message", spout);
        inputStream.each(new Fields("message"), new MessageTypeFilter())
                .partitionPersist(new DruidStateFactory(config), new Fields("message"), new DruidStateUpdater());
        return topology.build();
    }
}
