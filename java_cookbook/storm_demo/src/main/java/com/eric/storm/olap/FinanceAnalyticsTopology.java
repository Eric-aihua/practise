package com.eric.storm.olap;

import org.apache.storm.generated.StormTopology;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

/**
 * Created by Eric on 2017/8/29.
 */
public class FinanceAnalyticsTopology {
    public static StormTopology buildTopology(){
        TridentTopology tridentTopology=new TridentTopology();
        FixEventSpout spout=new FixEventSpout();
        Stream stream=tridentTopology.newStream("druid-stream", spout);
        stream.each(new Fields("message"),new MessageTypeFilter()).partitionPersist(new DruidStateFactory(),new Fields("message"),new DruidStateUpdater());
        return tridentTopology.build();
    }
}
