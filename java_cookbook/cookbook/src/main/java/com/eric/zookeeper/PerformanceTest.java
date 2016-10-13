package com.eric.zookeeper;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.log4j.PropertyConfigurator;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;

public class PerformanceTest {
    /**
     * server列表, 以逗号分割
     */
    protected static String hosts = "native-lufanfeng-2-5-24-138:2181,native-lufanfeng-3-5-24-139:2181,native-lufanfeng-4-5-24-140:2181";
    /**
     * 连接的超时时间, 毫秒
     */
    private final int SESSION_TIMEOUT = 5000;
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    protected static ZooKeeper zk;
    private static String nodePath = "/Test/test1";

    //static String data = "a very long string about data to set to zookeeper";
    static int threads = 4;    //线程数
    static int runs = 100;     //迭代次数
    static int start = 0;       //none

    static int size = 1024*4;   //写入数据的大小,单位：字节
    static byte[] testdata;     //测试数据

    public static void main(String[] args) throws Exception {
//        PropertyConfigurator.configure("log4j.properties");
        //生成写入的数据，大小size字节


        testdata = new byte[size];
        for(int i=0;i<size;i++){
            testdata[i] = 'A';
        }

        PerformanceTest test = new PerformanceTest();
        //连接
        test.connect();

        if (zk.exists(nodePath,false)==null){
            zk.create("/Test","0".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zk.create(nodePath,"0".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }

        WorkerStat[] statArray = new WorkerStat[threads];
        Thread[] threadArray = new Thread[threads];

        WorkerStat mainStat = new WorkerStat();
        mainStat.runs = runs * threads;

        long begin = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            statArray[i] = new WorkerStat();
            statArray[i].start = start + i * runs;
            statArray[i].runs = runs;
            threadArray[i] = new SetterThread(statArray[i]);
            threadArray[i].start();
        }
        for (int i = 0; i < threads; i++) {
            threadArray[i].join();
        }
        mainStat.setterTime = System.currentTimeMillis() - begin;

        begin = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            threadArray[i] = new GetterThread(statArray[i]);
            threadArray[i].start();
        }
        for (int i = 0; i < threads; i++) {
            threadArray[i].join();
        }
        mainStat.getterTime = System.currentTimeMillis() - begin;

        WorkerStat totalStat = new WorkerStat();

        System.out.println("Test over!!");
        System.out.println("Thread("+threads+")\truns\tset time(ms)\tget time(ms)");

        for (int i = 0; i < threads; i++) {
            totalStat.runs = totalStat.runs + statArray[i].runs;
            totalStat.setterTime = totalStat.setterTime + statArray[i].setterTime;
            totalStat.getterTime = totalStat.getterTime + statArray[i].getterTime;
        }
        System.out.println("Total\t\t" + totalStat.runs + "\t"+ totalStat.setterTime + "\t\t" + totalStat.getterTime);
        System.out.println("Avg\t\t" + runs + "\t" + totalStat.setterTime/ threads + "\t\t" + totalStat.getterTime / threads);
        System.out.println("TPS\t\t\t" + 1000 * totalStat.runs/ totalStat.setterTime + "\t\t" + 1000 * totalStat.runs/ totalStat.getterTime);
        System.out.println("\nMain\t\t" + mainStat.runs + "\t"+ mainStat.setterTime + "\t\t" + mainStat.getterTime);
        System.out.println("TPS\t\t" + 1000 * mainStat.runs/ mainStat.setterTime + "\t\t" + 1000 * mainStat.runs/ mainStat.getterTime);
    }

    private static class WorkerStat {
        public int start, runs;
        public long setterTime, getterTime;

        public WorkerStat() {
            start = runs = 0;
            setterTime = getterTime = 0;
        }
    }

    private static class SetterThread extends Thread {
        private WorkerStat stat;

        SetterThread(WorkerStat stat) {
            this.stat = stat;
        }

        public void run() {
            long begin = System.currentTimeMillis();

            for (int i = stat.start; i < stat.start + stat.runs; i++) {
                //写入节点数据
                try {
                    zk.setData(nodePath, testdata, -1);
                    System.out.println("set data"+testdata+" successful on thread"+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            stat.setterTime = end - begin;
        }
    }

    private static class GetterThread extends Thread {
        private WorkerStat stat;
        GetterThread(WorkerStat stat) {
            this.stat = stat;
        }
        public void run() {
            long begin = System.currentTimeMillis();
            for (int i = stat.start; i < stat.start + stat.runs; i++) {
                //读取节点数据
                try {
                    zk.getData(nodePath, false, null);
                    System.out.println("get data from path"+nodePath+" successful on thread"+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            stat.getterTime = end - begin;
        }
    }

    //===============================================================================
    /**
     * 连接zookeeper server
     */
    public void connect() throws Exception {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, new ConnWatcher());
        // 等待连接完成
        connectedSignal.await();
    }

    /**
     *
     * @author Kiven
     *
     */
    public class ConnWatcher implements Watcher{
        public void process(WatchedEvent event) {
            // 连接建立, 回调process接口时, 其event.getState()为KeeperState.SyncConnected
            if (event.getState() == KeeperState.SyncConnected) {
                // 放开闸门, wait在connect方法上的线程将被唤醒
                connectedSignal.countDown();
            }
        }
    }

    /**
     以下为各个参数的详细说明:
     path. znode的路径.
     data. 与znode关联的数据.
     acl. 指定权限信息, 如果不想指定权限, 可以传入Ids.OPEN_ACL_UNSAFE.
     指定znode类型. CreateMode是一个枚举类, 从中选择一个成员传入即可.
     */

    /**
     * 创建持久化节点
     */
    public void create(String Path, byte[] data) throws Exception {
        zk.create(Path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建节点:"+Path);
        System.out.println("=================");
    }

    /**
     *
     *获取节点信息
     *@author kiven
     *@createDate 2013-01-16 15:17:22
     *@param path
     *@throws KeeperException
     *@throws InterruptedException
     */
    public void getChild(String path) throws KeeperException, InterruptedException{
        try{
            List list=zk.getChildren(path, false);
            if(list.isEmpty()){
                System.out.println(path+"中没有节点");
            }else{
                System.out.println(path+"中存在节点");
                for(Object child:list){
                    System.out.println("节点为："+child);
                }
            }
        }catch (KeeperException.NoNodeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置节点数据
     * @throws Exception
     */
    public void setData(String path,String data) throws Exception{
        zk.setData(path, data.getBytes(), -1);
        System.out.println("set Data:"+"testSetData");
    }

    /**
     * 读取节点数据
     * @throws Exception
     */
    public void getData() throws Exception{
        System.out.println("get Data:");
        zk.getData(nodePath, false, null);
    }

    /**
     * 删除节点
     * @param path
     * @throws Exception
     */
    public void delete(String path) throws Exception{
        System.out.println("删除节点:"+path);
        //如果版本号与znode的版本号不一致，将无法删除，是一种乐观加锁机制；如果将版本号设置为-1，不会去检测版本，直接删除；
        zk.delete(path, -1);
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
