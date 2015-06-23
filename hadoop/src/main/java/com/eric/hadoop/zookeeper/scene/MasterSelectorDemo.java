package com.eric.hadoop.zookeeper.scene;

import java.util.Date;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 通过Curator实现Master节点的竞争选取
 * 
 * @author aihua.sun
 */
public class MasterSelectorDemo {
	private static final String IPS = "localhost:2181";
	private static final int SESSION_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;
	private static final String NAMESPACE = "curatortest";

	public static void main(String[] args) {
		election();
	}

	// 创建Session
	public static void election() {
		try {
			String electionPath = "/elector_path";
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
					.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
					.namespace(NAMESPACE).build();
			client.start();
			LeaderSelector selector = new LeaderSelector(client, electionPath, new LeaderSelectorListenerAdapter() {

				@Override
				public void takeLeadership(CuratorFramework client) throws Exception {
					// 该方法在竞争到Master时被执行，执行完该方法或者进程被终止时会释放Master的权利，重新开始一轮选举
					System.out.println(new Date()+" 线程：" + Thread.currentThread() + " 成为Master,将执行Master的操作");
					Thread.sleep(50000);
					System.out.println(new Date()+" 线程：" + Thread.currentThread() + " 执行完Master的操作,释放Master的权限");
				}
			});
			selector.autoRequeue();
			selector.start();
			Thread.sleep(Integer.MAX_VALUE);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
