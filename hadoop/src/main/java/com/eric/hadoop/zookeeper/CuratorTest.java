package com.eric.hadoop.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorTest {
	private static final String IPS = "localhost:2181";
	private static final int SESSION_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;
	private static final String NAMESPACE="curatortest";

	public static void main(String[] args) {
		testGetData();
	}
	
	//使用Fluent 风格的API创建Node,并获取data
	public static void testGetData() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT)
				.retryPolicy(retryPolicy)
				.namespace(NAMESPACE)
				.build();
		client.start();
		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/testnode1/ddd/sdfasdf","result".getBytes());
			Stat stat=new Stat();
			System.out.println("Get Data Result:"+new String(client.getData().storingStatIn(stat).forPath("/testnode1/ddd/sdfasdf")));
			System.out.println("Get Data Stat:"+stat);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//使用Fluent 风格的API删除Node
	public static void testDeleteNode() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT)
				.retryPolicy(retryPolicy)
				.namespace(NAMESPACE)
				.build();
		client.start();
		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/testnode1/aaa/ccc/eee/fff/hhh/jjj","result".getBytes());
			//删除/testnode1/ddd 节点，并连其子节点也会被删除
			//添加guaranteed保证数据可以删掉
			client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/testnode1/aaa");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//使用Fluent 风格的API创建Node
		public static void testCreatePathsWithMode() {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
					.sessionTimeoutMs(SESSION_TIMEOUT)
					.connectionTimeoutMs(CONNECT_TIMEOUT)
					.retryPolicy(retryPolicy)
					.namespace(NAMESPACE)
					.build();
			client.start();
			try {
				client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/testnode1/ddd/ccc","result".getBytes());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	
	//使用Fluent 风格的API创建带有命名空间的Session
	public static void testCreateNameSpaceSession() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT)
				.retryPolicy(retryPolicy)
				.namespace(NAMESPACE)
				.build();
		client.start();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//使用Fluent 风格的API创建Session
	public static void testCreateSessionByFluent() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT)
				.retryPolicy(retryPolicy)
				.build();
		client.start();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//创建Session
	public static void testCreateSession() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(IPS, SESSION_TIMEOUT, CONNECT_TIMEOUT, retryPolicy);
		client.start();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
