package com.eric.hadoop.zookeeper.scene;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 通过使用Curator实现分布式锁，实现数据的一致性
 * 
 * @author aihua.sun
 */
public class DistributedLock {

	private static final String IPS = "localhost:2181";
	private static final int SESSION_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;
	private static final String NAMESPACE = "curatortest";

	public static void main(String[] args) {
		curatorDistributeLock();
	}

	/**
	 * 通过Curator分布式锁的方式实现生成唯一订单号的程序，用Curator保证订单号的唯一
	 */
	public static void curatorDistributeLock() {
		String lockPath = "/distribute_lock";
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.namespace(NAMESPACE).build();
		client.start();
		final InterProcessMutex lock = new InterProcessMutex(client, lockPath);
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					try {
						lock.acquire();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
						String orderNo = simpleDateFormat.format(new Date());
						System.out.println(Thread.currentThread() + " " + orderNo);
						lock.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		es.shutdown();
	}

	/**
	 * 通过非分布式锁的方式实现生成订单号的程序,该程序会出现很多相同的订单号
	 */
	public static void noDistributeLock() {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
					String orderNo = simpleDateFormat.format(new Date());
					System.out.println(Thread.currentThread() + " " + orderNo);
				}
			});
		}
		es.shutdown();
	}

}
