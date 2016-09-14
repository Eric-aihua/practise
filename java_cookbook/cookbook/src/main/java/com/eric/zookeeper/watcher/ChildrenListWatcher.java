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

public class ChildrenListWatcher extends BaseWatcher {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ChildrenListWatcher.class);


    public static BaseWatcher getInstance() {
        if (watcher == null) {
            watcher = new ChildrenListWatcher();
        }
        return watcher;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (Event.EventType.NodeChildrenChanged == watchedEvent.getType()) {
                try {
                    System.out.println("##################New Node List:" + zookeeper.getChildren(watchedEvent.getPath(), true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
