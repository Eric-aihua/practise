package com.eric.zookeeper;


import java.io.IOException;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.eric.zookeeper.watcher.BaseWatcher;
import com.eric.zookeeper.watcher.ChildrenListWatcher;
import com.eric.zookeeper.watcher.NodeDataWatcher;
import com.eric.zookeeper.watcher.NodeExistWatcher;

/**
 * 类描述
 *
 * @author aihua.sun
 * @date 2015/5/20
 * @since V1.0
 */

public class ZookeeperTest {
    private static final String IPS = "native-lufanfeng-2-5-24-138:2181,native-lufanfeng-3-5-24-139:2181,native-lufanfeng-4-5-24-140:2181";
    private static final int TIMEOUT = 5000;
    private static final long TIME = System.currentTimeMillis();

    public static void main(String args[]) {
        testGetNodeList();
        
    }
    
    


    //通过添加AuthInfo的方式来实现权限验证
    public static void testAuthOperator() {
        try {
            String path = "/authtest";
            ZooKeeper authZooKeeper = new ZooKeeper(IPS, TIMEOUT, null);
            authZooKeeper.addAuthInfo("digest", "user:eric".getBytes());
            authZooKeeper.create(path, "encrypt data".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
            //获取data是由于没有auth会报错
            ZooKeeper noAuthZookeeper = new ZooKeeper(IPS, TIMEOUT, null);
            System.out.println("#############GetData Result:" + new String(noAuthZookeeper.getData(path, false, null)));

            //可以获取正确的data
            ZooKeeper correctAuthZookeeper = new ZooKeeper(IPS, TIMEOUT, null);
            correctAuthZookeeper.addAuthInfo("digest", "user:eric".getBytes());
            System.out.println("#############GetData Result:" + new String(correctAuthZookeeper.getData(path, false, null)));

//            获取data是由于没有正确的auth会报错
            ZooKeeper inCorrectAuthZookeeper = new ZooKeeper(IPS, TIMEOUT, null);
            inCorrectAuthZookeeper.addAuthInfo("digest", "user:simon".getBytes());
            System.out.println("#############GetData Result:" + new String(inCorrectAuthZookeeper.getData(path, false, null)));


        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过调用exist方法实现对节点的监控，
    public static void testNodeExistData() {
        try {
            String path = "/testupdate";
            ZooKeeper zooKeeper = NodeExistWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
            zooKeeper.exists(path, true);
            zooKeeper.create(path, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zooKeeper.setData(path, "newdata".getBytes(), -1);
            zooKeeper.delete(path, -1);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //添加临时的Node,对该几点的值进行更行，并通过NodeDataWatcher对该Node值得变化惊醒Monitor
    public static void testGetAndSetNodeData() {
        try {
            String path = "/testupdate";
            ZooKeeper zooKeeper = NodeDataWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
            zooKeeper.create(path, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("GetData Phrase 1 Value:" + new String(zooKeeper.getData(path, true, NodeDataWatcher.getInstance().getStat())));
            Stat stat = NodeDataWatcher.getInstance().getStat();
            System.out.println("GetData Phrase 1 State:" + stat.getCzxid() + " " + stat.getMzxid() + " " + stat.getVersion());
            zooKeeper.setData(path, "newdata".getBytes(), -1);

            System.out.println("GetData Phrase 2 Value:" + new String(zooKeeper.getData(path, true, NodeDataWatcher.getInstance().getStat())));
            System.out.println("GetData Phrase 2 State:" + stat.getCzxid() + " " + stat.getMzxid() + " " + stat.getVersion());


            zooKeeper.setData(path, "newdata".getBytes(), -1);

            System.out.println("GetData Phrase 3 Value:" + new String(zooKeeper.getData(path, true, NodeDataWatcher.getInstance().getStat())));
            System.out.println("GetData Phrase 3 State:" + stat.getCzxid() + " " + stat.getMzxid() + " " + stat.getVersion());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //同步获取 Node 列表,当有新的节点加进来的时候，会触发ChildrenListWatcher的Process方法
    public static void testGetNodeList() {
        try {
            String path = "/test";
            ZooKeeper zooKeeper = ChildrenListWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
//            String root = zooKeeper.create(path, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            System.out.println("Node:" + root);
//            zooKeeper.create(path + "/" + System.currentTimeMillis(), "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("New NodeList:" + zooKeeper.getChildren(path, true));
//            Thread.sleep(5000000);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //异步临时会话
    public static void testCreateAsynTempNode() {
        String path = "/test" + TIME;
        ZooKeeper zooKeeper = BaseWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
        zooKeeper.create(path, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
//            @Override
            public void processResult(int returnCode, String path, Object context, String name) {
                System.out.println("Create Path Result:" + returnCode);
                System.out.println("Create Path Path:" + path);
                System.out.println("Create Path Context:" + context);
                System.out.println("Create Path RealPathName:" + name);
            }
        }, "CallBack Param Object.......");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //会话结束，节点任然存在
    public static void testCreatePersistenceNode() {
        try {
            String path = "/test" + TIME;
            ZooKeeper zooKeeper = BaseWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
            String result = zooKeeper.create(path, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("Node:" + result);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //会话结束，节点被删除
    public static void testCreateTempNode() {
        try {
            String path = "/test";
            ZooKeeper zooKeeper = BaseWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
            String result = zooKeeper.create(path, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("Node:" + result);
            Thread.sleep(5000);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //获得会话
    public static void testGetSession() {
        ZooKeeper zooKeeper = BaseWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT);
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();
        ZooKeeper zooKeeperInvalidSession = BaseWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT, 1l, "".getBytes());
        ZooKeeper zooKeeperValidSession = BaseWatcher.getInstance().getZookeeperSession(IPS, TIMEOUT, sessionId, passwd);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(zooKeeper.getState());
        System.out.println(zooKeeperInvalidSession.getState());
        System.out.println(zooKeeperValidSession.getState());
    }
}
