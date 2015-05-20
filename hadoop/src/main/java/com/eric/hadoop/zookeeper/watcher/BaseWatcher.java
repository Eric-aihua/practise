package com.eric.hadoop.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 类描述
 *
 * @author aihua.sun
 * @date 2015/5/20
 * @since V1.0
 */

public class BaseWatcher implements Watcher {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BaseWatcher.class);
    protected static final CountDownLatch countDownLatch = new CountDownLatch(1);
    protected static ZooKeeper zookeeper = null;
    protected static BaseWatcher watcher;
    protected static Stat stat = new Stat();

    public static BaseWatcher getInstance() {
        if (watcher == null) {
            watcher = new BaseWatcher();
        }
        return watcher;
    }

    public ZooKeeper getZookeeperSession(String ips, int timeOut) {
        try {
            zookeeper = new ZooKeeper(ips, timeOut, this);
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zookeeper;
    }

    public ZooKeeper getZookeeperSession(String ips, int timeOut, long sessionId, byte[] passwd) {
        try {
            zookeeper = new ZooKeeper(ips, timeOut, this, sessionId, passwd);
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zookeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        LOG.info("##################Received Event:" + watchedEvent.toString());
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }


    public Stat getStat(){
        return stat;
    }
}
