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
        config.put("druid.tranquility.zk.connect", "10.5.25.18:2181");
        config.setNumWorkers(3);
        LocalCluster localCluster=new LocalCluster();
        StormTopology stormTopology= FinanceAnalyticsTopology.buildTopology();
        localCluster.submitTopology("Druid-Intergration",config,stormTopology);
        Thread.sleep(10000);
        localCluster.shutdown();
    }
}
