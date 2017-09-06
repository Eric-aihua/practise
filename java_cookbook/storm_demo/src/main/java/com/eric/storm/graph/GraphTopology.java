package com.eric.storm.graph;

import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.StringMultiSchemeWithTopic;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.trident.TransactionalTridentKafkaSpout;
import org.apache.storm.kafka.trident.TridentKafkaConfig;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

/**
 * Created by Eric on 2017/8/29.
 */
public class GraphTopology {
    public static StormTopology buildTopology(){
        TridentTopology tridentTopology=new TridentTopology();
        String kafkaTopic="stream-log";
        TridentKafkaConfig spoutConfig=new TridentKafkaConfig(new ZkHosts("10.1.110.24:2181,10.1.110.22:2181"),kafkaTopic);
        spoutConfig.scheme=new StringMultiSchemeWithTopic();
        spoutConfig.startOffsetTime=-2;
        TransactionalTridentKafkaSpout kafkaSpout=new TransactionalTridentKafkaSpout (spoutConfig);
        Stream stream=tridentTopology.newStream("twitter-graph",kafkaSpout);
        Fields JsonFields=new Fields("timestamp","message");
        stream.each(new Fields("twitter_stream"),new JSONProjectFunction(JsonFields),JsonFields).project(JsonFields)
                .partitionPersist(new GraphStateFactory(new Neo4JGraphFactory()),JsonFields,new GraphUpdater(new TwitterGraphTupleProcessor()),new Fields());
        return tridentTopology.build();
    }
}
