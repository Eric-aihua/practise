package com.eric.storm.graph;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;

/**
 * Created by Eric on 2017/8/29.
 */
public class Main {
    public static void main(String args[]) throws InterruptedException {
        Config config=new Config();
        LocalCluster localCluster=new LocalCluster();
        StormTopology stormTopology= GraphTopology.buildTopology();
        localCluster.submitTopology("TwitterGraph",config,stormTopology);
        Thread.sleep(10000);
        localCluster.shutdown();
    }
}
