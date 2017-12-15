package com.eric.storm.olap;

import org.apache.storm.druid.bolt.DruidBeamFactory;
import org.apache.storm.druid.bolt.ITupleDruidEventMapper;
import org.apache.storm.druid.bolt.TupleDruidEventMapper;
import org.apache.storm.druid.trident.DruidBeamStateFactory;
import org.apache.storm.druid.trident.DruidBeamStateUpdater;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 2017/8/29.
 */
public class FinanceAnalyticsTopology {
    public static StormTopology buildTopology(){
        TridentTopology tridentTopology=new TridentTopology();
        DruidBeamFactory druidBeamFactory = new SampleDruidBeamFactoryImpl(new HashMap<String, Object>());
        ITupleDruidEventMapper<Map<String, Object>> eventMapper = new TupleDruidEventMapper<>(TupleDruidEventMapper.DEFAULT_FIELD_NAME);
        Stream stream=tridentTopology.newStream("druid-stream", new SimpleBatchSpout(10));
        stream.each(new Fields(TupleDruidEventMapper.DEFAULT_FIELD_NAME),new MessageTypeFilter()).partitionPersist(new DruidBeamStateFactory<Map<String, Object>>(druidBeamFactory, eventMapper), new Fields("event"), new DruidBeamStateUpdater());
        return tridentTopology.build();
    }
}
