package com.eric.storm.olap;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Eric on 2017/10/26.
 */
public class FixEventEmitter<T> implements ITridentSpout.Emitter<Long>,Serializable {

    private static final long serialVersionUID = -778756559681342455L;
    public static AtomicInteger succesfulTransactions=new AtomicInteger(0);
    public static AtomicInteger uids=new AtomicInteger(0);

    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector tridentCollector) {
        for (int i=0; i<100;i++){

            Random random=new Random();
            String mid=UUID.randomUUID().toString();
            String msgType=random.nextInt(10)+"";
            Float price=random.nextFloat()*10;
            String symbol=random.nextInt(10)+"";
            FixMessageDto dto=new FixMessageDto(mid,msgType,price,symbol);
            List<Object> message=new ArrayList<Object>();
            message.add(dto);
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
