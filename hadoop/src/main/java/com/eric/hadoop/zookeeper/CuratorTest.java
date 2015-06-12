package com.eric.hadoop.zookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorTest {
	private static final String IPS = "localhost:2181";
	private static final int SESSION_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;
	private static final String NAMESPACE = "curatortest";
	private static CountDownLatch smaphore = new CountDownLatch(2);
	private static ExecutorService executorService = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		testDeleteNode();
	}

	// Node变化监听,主要用于监听节点的是否存在

	public static void testChildNodeChangeListener() {
		String path = "/childnodelistener3";
		try {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
					.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
					.namespace(NAMESPACE).build();
			client.start();
			
			Thread.sleep(1000);
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "result".getBytes());
			final PathChildrenCache nodeCache = new PathChildrenCache(client, path, true);
			nodeCache.start();
			nodeCache.getListenable().addListener(new PathChildrenCacheListener() {

				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					System.out.println("##############Received Event:"+event.getType()+" Event Path:"+event.getData().getPath()+" Event Data:"+new String(event.getData().getData()));
					switch (event.getType()){
						case CHILD_ADDED:
							System.out.println("##############New Child was added:"+event.getData().getPath());
							break;
						case CHILD_UPDATED:
							System.out.println("##############Child was updated:"+event.getData().getPath()+" New Value:"+new String(event.getData().getData()));
							break;
						case CHILD_REMOVED:
							System.out.println("##############New Child was removed:"+event.getData().getPath());
							break;
						default :
							break;
					}
				}
			});
			Thread.sleep(1000);
			client.setData().forPath(path, "new data".getBytes());
			Thread.sleep(1000);
			String child1Path=path+"/node1";
			client.create().withMode(CreateMode.PERSISTENT).forPath(child1Path, "result".getBytes());
			client.setData().forPath(child1Path, "new Data".getBytes());
			Thread.sleep(1000);
			String child2Path=path+"/node2";
			client.create().withMode(CreateMode.PERSISTENT).forPath(child2Path, "result".getBytes());
			Thread.sleep(1000);
			client.delete().forPath(child2Path);
			Thread.sleep(3000);
			client.delete().deletingChildrenIfNeeded().forPath(path);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Node变化监听,主要用于监听节点的是否存在

	public static void testNodeChangeListener() {
		String path = "/listener";
		try {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
					.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
					.namespace(NAMESPACE).build();
			client.start();
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "result".getBytes());
			final NodeCache nodeCache = new NodeCache(client, path, false);
			nodeCache.start();
			nodeCache.getListenable().addListener(new NodeCacheListener() {
				@Override
				public void nodeChanged() throws Exception {
					System.out.println("###########Update Data Path:"
							+ new String(nodeCache.getCurrentData().getPath()));
					System.out.println("###########Update Data Context:"
							+ new String(nodeCache.getCurrentData().getData()));
				}
			});
			Thread.sleep(1000);
			client.setData().forPath(path, "new data".getBytes());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 异步接口的列子
	public static void testAsyncInterface() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.namespace(NAMESPACE).build();
		client.start();
		try {
			// 使用不带ExecutorService的异步回调,用的是EventThread的线程
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
					.inBackground(new BackgroundCallback() {
						@Override
						public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
							System.out.println("Client:" + client.toString() + " Event:" + event.getResultCode()
									+ " Event Type" + event.getType());
							System.out.println("Thread:" + Thread.currentThread());
							smaphore.countDown();
						}
					}).forPath("/noexecutor", "result".getBytes());
			// 使用带ExecutorService的异步回调，用的是自定义的ExecutorService,适合处理比较耗时的回调函数
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
					.inBackground(new BackgroundCallback() {
						@Override
						public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
							System.out.println("Client:" + client.toString() + " Event:" + event.getResultCode()
									+ " Event Type" + event.getType());
							System.out.println("Thread:" + Thread.currentThread());
							smaphore.countDown();
						}
					}, executorService).forPath("/executor", "result".getBytes());
			Thread.sleep(4000);
			smaphore.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 保证自定义的ExecutorService始终可以被关闭
			executorService.shutdown();
		}
	}

	// 使用Fluent 风格的API创建Node,并获取data
	public static void testGetData() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.namespace(NAMESPACE).build();
		client.start();
		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
					.forPath("/testnode1/ddd/sdfasdf", "result".getBytes());
			Stat stat = new Stat();
			System.out.println("Get Data Result:"
					+ new String(client.getData().storingStatIn(stat).forPath("/testnode1/ddd/sdfasdf")));
			System.out.println("Get Data Stat:" + stat);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 使用Fluent 风格的API删除Node
	public static void testDeleteNode() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
//				.namespace(NAMESPACE)
				.build();
		client.start();
		try {
//			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
//					.forPath("/testnode1/aaa/ccc/eee/fff/hhh/jjj", "result".getBytes());
			// 删除/testnode1/ddd 节点，并连其子节点也会被删除
			// 添加guaranteed保证数据可以删掉
			client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/testnode1");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 使用Fluent 风格的API创建Node
	public static void testCreatePathsWithMode() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.namespace(NAMESPACE).build();
		client.start();
		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
					.forPath("/testnode1/ddd/ccc", "result".getBytes());
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 使用Fluent 风格的API创建带有命名空间的Session
	public static void testCreateNameSpaceSession() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.namespace(NAMESPACE).build();
		client.start();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 使用Fluent 风格的API创建Session
	public static void testCreateSessionByFluent() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.build();
		client.start();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 创建Session
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
