package com.eric.storm.trident.windows.ewma;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;

/**
 * 移动窗口数据平均值的统计
 * 本程序主要用来演示如何从数据流中识别模式()，例如对于特定时间的发生概率或是计数到达了某个阀值时，给予报警，应用场景包括
 * 1. 应用监控：日志中的错误达到一定比例后，发出警告
 * 2.入侵检测：对于异常登录行为的检测，例如登录失败次数一直增加
 * 3. 在线推荐：发现流行趋势，并实时调整广告策略
 * Created by Eric on 2017/8/25.
 */
public class Main {
    public static void main(String args[]) throws InterruptedException {
        Config config=new Config();
        LocalCluster localCluster=new LocalCluster();
        StormTopology stormTopology= RealTimeTrendTopology.buildTopology();
        localCluster.submitTopology("EWMA_DEMO",config,stormTopology);
        Thread.sleep(100000);
        localCluster.shutdown();
    }
}
