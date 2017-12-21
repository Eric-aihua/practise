package com.eric.storm.olap;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;

/**
 * 本package代码主要演示如何使用Storm与非事务性的OLAP框架进行集成(例如 Druid)
 * Created by Eric on 2017/8/29.
 */
public class Main {
    public static void main(String args[]) throws InterruptedException {
        Config config=new Config();
        config.setDebug(true);
        config.setNumWorkers(3);
        config.put("zk_cluster","native-lufanfeng-2-5-24-138:2181,native-lufanfeng-3-5-24-139:2181,native-lufanfeng-4-5-24-140:2181");
        LocalCluster localCluster=new LocalCluster();
        StormTopology stormTopology= TrafficStatSipTopology.buildTopology(config);
        localCluster.submitTopology("Druid-Intergration",config,stormTopology);
        Thread.sleep(10000);
        localCluster.shutdown();
    }
}
