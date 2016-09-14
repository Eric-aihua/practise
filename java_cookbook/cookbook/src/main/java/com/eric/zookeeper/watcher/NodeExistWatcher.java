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

public class NodeExistWatcher extends BaseWatcher {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NodeExistWatcher.class);


    public static BaseWatcher getInstance() {
        if (watcher == null) {
            watcher = new NodeExistWatcher();
        }
        return watcher;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            try {
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    countDownLatch.countDown();
                } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                    System.out.println("##################New Value Updated:" + watchedEvent.getPath());
                    zookeeper.exists(watchedEvent.getPath(), true);

                } else if (Event.EventType.NodeCreated == watchedEvent.getType()) {
                    System.out.println("##################New Node Created:" + watchedEvent.getPath());
                    zookeeper.exists(watchedEvent.getPath(), true);

                } else if (Event.EventType.NodeDeleted == watchedEvent.getType()) {
                    zookeeper.exists(watchedEvent.getPath(), true);
                    System.out.println("##################New Node Deleted:" + watchedEvent.getPath());
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
