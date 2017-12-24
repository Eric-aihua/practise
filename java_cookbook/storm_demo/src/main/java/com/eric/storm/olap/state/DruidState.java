package com.eric.storm.olap.state;

import com.eric.storm.olap.DruidTranquilitySender;
import com.eric.storm.olap.FixMessageDto;
import org.apache.storm.trident.state.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Eric on 2017/12/20.
 */
public class DruidState  implements State {

    private Logger LOG= LoggerFactory.getLogger(this.getClass());
    private int partitionIndex;
    private DruidTranquilitySender sender;
    private List<Map<String, Object>> messages = new ArrayList<Map<String, Object>>();

    public DruidState(DruidTranquilitySender sender, int partitionIndex) {
        this.partitionIndex=partitionIndex;
        this.sender=sender;
        this.sender.startSender();
    }

    @Override
    public void beginCommit(Long aLong) {
//        this.sender.startSender();
    }

    @Override
    public void commit(Long batchId) {
        String partitionId = batchId.toString() + "-" + partitionIndex;
        LOG.info("Committing partition [" + partitionIndex + "] of batch [" + batchId + "]");
        try {
            if (sender.STATUS.isCompleted(partitionId)) {
                LOG.warn("Encountered completed partition [" + partitionIndex + "] of batch [" + batchId + "]");
                return;
            } else if (sender.STATUS.isInLimbo(partitionId)) {
                LOG.warn("Encountered limbo partition [" + partitionIndex + "] of batch [" + batchId + "] : NOTIFY THE AUTHORITIES!");
                return;
            } else if (sender.STATUS.isInProgress(partitionId)) {
                LOG.warn("Encountered in-progress partition [\" + partitionIndex + \"] of batch [" + batchId + "] : NOTIFY THE AUTHORITIES!");
                return;
            }
            // TODO 状态维护
//            sender.STATUS.putInProgress(partitionId);
            sender.sendMsg(partitionId, messages);
        } catch (Exception e) {
            LOG.error("Could not start firehose for [" + partitionIndex + "] of batch [" + batchId + "]", e);
        }finally {
            sender.finishedSender();
        }
    }

    public void aggregateMessage(Map<String, Object> message) {
        // LOG.info("Aggregating [" + message + "]");
        messages.add(message);
    }
}
