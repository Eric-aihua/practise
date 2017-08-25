package com.eric.storm.trident.windows.ewma;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.topology.TransactionAttempt;
import org.apache.storm.utils.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 模拟系统生成的告警日志
 * Created by Eric on 2017/8/25.
 */
public class RealTImeTrendEmmitter implements ITridentSpout.Emitter<Long> ,Serializable{
    private static final long serialVersionUID = -7042626272758266740L;

    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector tridentCollector) {
        Random random=new Random();
        try {
            int randomValue=random.nextInt(100);
            Time.sleep(1000);
            for (int i=0;i<randomValue;i++){
                List<Object> logs=new ArrayList<Object>();
                // 模拟系统生成的告警日志
                logs.add(System.currentTimeMillis()+" : WARN:System slowly..............");
                logs.add(System.currentTimeMillis());
                tridentCollector.emit(logs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void success(TransactionAttempt transactionAttempt) {

    }

    @Override
    public void close() {

    }
    public static void main(String args[]){
        Random random=new Random();
        System.out.println(random.nextInt(10));

    }
}
