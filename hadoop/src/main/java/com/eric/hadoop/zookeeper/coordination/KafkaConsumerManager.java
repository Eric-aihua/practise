package com.eric.hadoop.zookeeper.coordination;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.codehaus.plexus.util.StringUtils;

/**
 * 根据KafkaConsumerManager运行的数量，动态的分配运行Consumer的进行数量
 * 
 * @author aihua.sun
 */
public class KafkaConsumerManager {
	private static final String KAFKA_PART_COUNT = "10";
	private static final String IPS = "localhost:2181";
	private static final int SESSION_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;
	private static final String NAMESPACE = "console_managers";
	private static final String KAFKA_PATH = "/kafka_count";
	private static final String MONITOR_NODES_PATH = "/nodes";
	private static final String ELECTION_PATH = "/elector_path";
	private CuratorFramework client;

	public static void main(String[] args) {
		KafkaConsumerManager kafkaConsumerManager = new KafkaConsumerManager();
		kafkaConsumerManager.initClient();
		kafkaConsumerManager.startConsumerThreads();
	}

	public void initClient() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.builder().connectString(IPS).sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy).namespace(NAMESPACE).build();
		client.start();
//		LeaderSelector selector = new LeaderSelector(client, ELECTION_PATH, new LeaderSelectorListenerAdapter() {
//			@Override
//			public void takeLeadership(CuratorFramework client) throws Exception {
//				// 该方法在竞争到Master时被执行，执行完该方法或者进程被终止时会释放Master的权利，重新开始一轮选举
//				System.out.println(new Date()+" 线程：" + Thread.currentThread() + " 成为Master,将执行Master的操作");
//				Thread.sleep(Integer.MAX_VALUE);
//				System.out.println(new Date()+" 线程：" + Thread.currentThread() + " 执行完Master的操作,释放Master的权限");
//			}
//		});
//		selector.autoRequeue();
//		selector.start();
	}

	public void startConsumerThreads() {
		setKafkaPartCount();
		addChildrenNodeIntoCluster();
		
		registerChildChangeListener();
		registerDataChangeListener();
	}

	private void setKafkaPartCount() {
		EnsurePath ensurePath = new EnsurePath("/" + NAMESPACE + KAFKA_PATH);
		try {
			ensurePath.ensure(client.getZookeeperClient());
			client.setData().forPath(KAFKA_PATH, KAFKA_PART_COUNT.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addChildrenNodeIntoCluster() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			String hostUUID = "/" + address.getHostName() + System.currentTimeMillis();
			// 创建节点的时候，默认放0
			client.create().withMode(CreateMode.EPHEMERAL).forPath(MONITOR_NODES_PATH + hostUUID, "0".getBytes());
			System.out.println("发送添加节点请求:" + hostUUID);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 节点变化的监听器 当节点数量发生变化的时候，重新分配每个节点运行的consumer进程数
	 */
	private void registerChildChangeListener() {
		final PathChildrenCache nodeCache = new PathChildrenCache(client, MONITOR_NODES_PATH, true);
		try {
			nodeCache.getListenable().addListener(new PathChildrenCacheListener() {
				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					switch (event.getType()) {
					case CHILD_ADDED:
						System.out.println("##############添加新节点:" + event.getData().getPath());
						reAlloctionConsumer();
						break;
					case CHILD_REMOVED:
						System.out.println("##############删除节点:" + event.getData().getPath());
						reAlloctionConsumer();
						break;
					default:
						break;
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 重新分配每个Monitor-Manager启动的消费者进程数
	private void reAlloctionConsumer() {
		String kafkaPartCountStr;
		try {
			kafkaPartCountStr = new String(client.getData().forPath(KAFKA_PATH));

			List<String> childrenList = client.getChildren().forPath(MONITOR_NODES_PATH);
			int childrenListSize = childrenList.size();
			if (StringUtils.isAlphanumeric(kafkaPartCountStr) && childrenListSize > 0) {
				int kafkaPartCount = Integer.valueOf(kafkaPartCountStr);
				Map<String, Integer> nodeConsuemerCounter = new HashMap<String, Integer>();
				for (int i = 1; i <= kafkaPartCount; i++) {
					String nodeName = childrenList.get(i % childrenListSize);
					if (nodeConsuemerCounter.get(nodeName) == null) {
						nodeConsuemerCounter.put(nodeName, 1);
					} else {
						int oldPartCount = nodeConsuemerCounter.get(nodeName);
						nodeConsuemerCounter.put(nodeName, oldPartCount + 1);
					}
				}

				for (String nodeName : nodeConsuemerCounter.keySet()) {
					client.setData().forPath(nodeName, nodeConsuemerCounter.get(nodeName).toString().getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void registerDataChangeListener() {

	}

}
