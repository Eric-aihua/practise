package com.eric.storm.olap;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.topology.TransactionAttempt;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Eric on 2017/10/26.
 */
public class FixEventEmitter<T> implements ITridentSpout.Emitter<Long>, Serializable {

    private static final long serialVersionUID = -778756559681342455L;
    public static AtomicInteger succesfulTransactions = new AtomicInteger(0);

    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector tridentCollector) {
        String[][] randomIPInfo = new String[100][3];
        String dipPrefix = "192.168";
        String sipPrefix = "222.168";
        String randomOrg = UUID.randomUUID().toString();
        String randomPartner = UUID.randomUUID().toString();
        String randomPG = UUID.randomUUID().toString();
        String randomDevice = UUID.randomUUID().toString();
        Random random = new Random();
        for (int index = 0; index < randomIPInfo.length; index++) {
            randomIPInfo[index][0] = dipPrefix + "." + random.nextInt(255) + "." + random.nextInt(255);
            randomIPInfo[index][1] = sipPrefix + "." + random.nextInt(255) + "." + random.nextInt(255);
        }
        for (int i = 0; i <10; i++) {
            // Build a sample event to send; make sure we use a current date
            String[] ipTuple = randomIPInfo[random.nextInt(randomIPInfo.length)];
            final Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("timestamp", new DateTime().toString());
            obj.put("org", randomOrg);
            obj.put("partner", randomPartner);
            obj.put("device_no", randomDevice);
            obj.put("pg", randomPG);
            obj.put("dip", ipTuple[0]);
            obj.put("sip", ipTuple[1]);
            obj.put("in_bps", random.nextDouble() * 100000000);
            obj.put("pa_bps", random.nextDouble() * 10000000);
            obj.put("dr_bps", random.nextDouble() * 1000000);
            obj.put("in_pps", random.nextDouble() * 1000000);
            obj.put("dr_pps", random.nextDouble() * 1000000);
            obj.put("pa_pps", random.nextDouble() * 1000000);
            List<Object> message = new ArrayList<Object>();
            message.add(obj);
            tridentCollector.emit(message);
        }
    }

    @Override
    public void success(TransactionAttempt transactionAttempt) {
        succesfulTransactions.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
