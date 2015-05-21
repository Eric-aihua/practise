package com.eric.hadoop.zookeeper.scene;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过使用Curator实现分布式锁，实现数据的一致性
 * 
 * @author aihua.sun
 */
public class DistributedLock {

	public static void main(String[] args) {
		noDistributeLock();
	}

	/**
	 * 通过非分布式锁的方式实现生成订单号的程序
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
