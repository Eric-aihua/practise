package com.eric.storm.trident.windows.outbreakdetector;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.topology.TransactionAttempt;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DiagnosisEventSpot implements ITridentSpout<Long> {
    private static final long serialVersionUID = -6129827932033584685L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private BatchCoordinator<Long> batchCoordinator = new DefaultBatchCoordinator<Long>();
    private Emitter<Long> emitter = new DiagnosisEmitter();

    @Override
    public BatchCoordinator getCoordinator(String s, Map map, TopologyContext topologyContext) {
        return batchCoordinator;
    }

    @Override
    public Emitter getEmitter(String s, Map map, TopologyContext topologyContext) {
        return emitter;
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("spout");
    }

}

/**
 * 依靠元数据，进行批次重放
 */
class DiagnosisEmitter implements ITridentSpout.Emitter,Serializable {
    private static final long serialVersionUID = -6393021026572921449L;
    private AtomicInteger successfulTransaction=new AtomicInteger();
    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Object o, TridentCollector tridentCollector) {
        for(int i=0;i<10000;i++){
            List<Object> events=new ArrayList<Object>();
            double lat=new Double(-30+(int)(Math.random()*75));
            double lng=new Double(-30+(int)(Math.random()*70));
            long time= System.currentTimeMillis();
            String diagCode=new Integer(320+(int)(Math.random()*7)).toString();
            events.add(new DiagnosisEvent(lat,lng,time,diagCode));
            tridentCollector.emit(events);
        }
    }

    @Override
    public void success(TransactionAttempt transactionAttempt) {
        successfulTransaction.incrementAndGet();
    }

    @Override
    public void close() {

    }


}

class DiagnosisEvent implements Serializable{
    private double lat;
    private double lng;
    private long time;
    private String diagCode;

    public DiagnosisEvent(double lat, double lng, long time, String diagCode) {
        this.lat = lat;
        this.lng = lng;
        this.time = time;
        this.diagCode = diagCode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    @Override
    public String toString() {
        return "DiagnosisEvent[" +
                "lat=" + lat +
                ", lng=" + lng +
                ", time=" + time +
                ", diagCode='" + diagCode + '\'' +
                ']';
    }
}


/**
 * 负责管理批次和元数据
 * @param <Long>
 */
class DefaultBatchCoordinator<Long> implements ITridentSpout.BatchCoordinator<Long> ,Serializable{
    private static final long serialVersionUID = -6129827932223584685L;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public Long initializeTransaction(long l, Long aLong, Long x1) {
        logger.info("InitTransaction for txid:"+l);
        return null;
    }

    @Override
    public void success(long l) {
        logger.info("Successful for txid:"+l);
    }

    @Override
    public boolean isReady(long l) {
        return true;
    }

    @Override
    public void close() {

    }
}
