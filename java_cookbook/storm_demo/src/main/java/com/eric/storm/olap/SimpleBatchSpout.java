package com.eric.storm.olap;

/**
 * Created by Eric on 2017/12/15.
 */
import org.apache.storm.Config;
import org.apache.storm.druid.bolt.TupleDruidEventMapper;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.IBatchSpout;
import org.apache.storm.tuple.Fields;
import org.joda.time.DateTime;

import java.util.*;

public class SimpleBatchSpout implements IBatchSpout {

    private int batchSize;
    private final Map<Long, List<List<Object>>> batches = new HashMap<>();
    public static final Fields DEFAULT_FIELDS = new Fields(TupleDruidEventMapper.DEFAULT_FIELD_NAME);

    public SimpleBatchSpout(int batchSize) {
        this.batchSize = batchSize;
    }


    @Override
    public void open(Map map, TopologyContext topologyContext) {

    }

    @Override
    public void emitBatch(long batchId, TridentCollector collector) {
        List<List<Object>> values;
        if(batches.containsKey(batchId)) {
            values = batches.get(batchId);
        } else {
            values = new ArrayList<>();
            for (int i = 0; i < batchSize; i++) {
                List<Object> value = new ArrayList<>();
                Random random=new Random();
                Map<String, Object> event = new LinkedHashMap<>();
                event.put("timestamp", new DateTime().toString());
                event.put("publisher", "foo.com");
                event.put("advertiser", "google.com");
                event.put("click", i);
                event.put("type", random.nextInt(10));
                value.add(event);
                values.add(value);
            }
            batches.put(batchId, values);
        }
        for (List<Object> value : values) {
            collector.emit(value);
        }

    }

    @Override
    public void ack(long batchId) {
        batches.remove(batchId);
    }

    @Override
    public void close() {
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config conf = new Config();
        conf.setMaxTaskParallelism(1);
        return conf;
    }

    @Override
    public Fields getOutputFields() {
        return DEFAULT_FIELDS;
    }
}