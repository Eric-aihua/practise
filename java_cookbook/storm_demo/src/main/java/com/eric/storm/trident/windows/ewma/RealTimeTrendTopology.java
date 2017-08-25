package com.eric.storm.trident.windows.ewma;

import org.apache.storm.generated.StormTopology;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

/**
 * Created by Eric on 2017/8/25.
 */
public class RealTimeTrendTopology {

    public static StormTopology buildTopology(){

        TridentTopology tridentTopology=new TridentTopology();
        RealTimeTrendSpout realTimeTrendSpout=new RealTimeTrendSpout();
        Stream stream=tridentTopology.newStream("RealTimeLog",realTimeTrendSpout);
        //计算移动平均值
        EWMA ewma= new EWMA().sliding(1.0, EWMA.Time.MINUTES).withAlpha(EWMA.ONE_MINUTE_ALPHA);
        stream.each(new Fields("RealTimeLog","LogTime"),new MovingAverageBolt(ewma,EWMA.Time.MINUTES),new Fields("average"))
                .each(new Fields("average"),new ThresholdFilterBolt(50d),new Fields("msg"))
                .each(new Fields("msg"),new XMPPSenderBolt(),new Fields());
        return tridentTopology.build();
    }
}
