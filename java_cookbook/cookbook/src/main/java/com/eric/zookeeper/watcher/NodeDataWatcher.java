package com.eric.zookeeper.watcher;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.LoggerFactory;

/**
 * 类描述
 *
 * @author aihua.sun
 * @date 2015/5/20
 * @since V1.0
 */

public class NodeDataWatcher extends BaseWatcher {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NodeDataWatcher.class);


    public static BaseWatcher getInstance() {
        if (watcher == null) {
            watcher = new NodeDataWatcher();
        }
        return watcher;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                try {
                    System.out.println("##################New Node Value:" + new String(zookeeper.getData(watchedEvent.getPath(), true, stat)));
                    System.out.println("##################New Value State" + stat.getCzxid()+" "+stat.getMzxid()+" "+stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
