package com.eric.storm.trident.windows.ewma;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 当产生日志的速率超过或是低于阀值时，会引起状态的变化，状态发生变化时，会将消息发送到下一个Bolt
 * Created by Eric on 2017/8/25.
 */
public class ThresholdFilterBolt  extends BaseFunction{
    private static final long serialVersionUID = 6740388974179026357L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private static enum State {
        BELOW, ABOVE;
    }
    private double threshold=0d;
    private State lastState=State.BELOW;

    public ThresholdFilterBolt(double threshold) {
        this.threshold=threshold;
    }

    @Override
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        double currentRate=(double)tridentTuple.get(0);
        State newState= currentRate>this.threshold? State.ABOVE:State.BELOW;
        if (newState != lastState){
            String msg="Log State Change, new state is : "+newState+"  Old State is:"+this.lastState+"  current rate is:"+currentRate;
            this.lastState=newState;
            logger.info(msg);
            tridentCollector.emit(Arrays.asList(msg));
        }
    }
}


