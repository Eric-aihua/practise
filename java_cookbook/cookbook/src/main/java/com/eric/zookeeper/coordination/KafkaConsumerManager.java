package com.eric.zookeeper.coordination;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;


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
	private static final String NAMESPACE = "monitor_managers";
	private static final String KAFKA_PATH = "/kafka_count";
	private static final String MONITOR_NODES_PATH = "/nodes";
	private static final String ALLOC_NODE = "/alloc_node";
	private String tempNodeId;
	private String oldConsumerThreadCount = "0";
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
	}

	public void startConsumerThreads() {
		registerKafkaPartCount();
		registerMonitorNodeIntoCluster();
		registerChildChangeListener();
		registerDataChangeListener();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void registerKafkaPartCount() {
		EnsurePath ensurePath = new EnsurePath("/" + NAMESPACE + KAFKA_PATH);
		try {
			ensurePath.ensure(client.getZookeeperClient());
			client.setData().forPath(KAFKA_PATH, KAFKA_PART_COUNT.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerMonitorNodeIntoCluster() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			tempNodeId = "/" + address.getHostName() + System.currentTimeMillis();
			EnsurePath ensureNodesPath = new EnsurePath("/" + NAMESPACE + MONITOR_NODES_PATH);
			EnsurePath ensureAllocPath = new EnsurePath("/" + NAMESPACE + ALLOC_NODE);
			ensureNodesPath.ensure(client.getZookeeperClient());
			ensureAllocPath.ensure(client.getZookeeperClient());
			// 创建节点的时候，默认放0
			client.create().withMode(CreateMode.EPHEMERAL).forPath(MONITOR_NODES_PATH + tempNodeId, "0".getBytes());
			client.setData().forPath(ALLOC_NODE, tempNodeId.getBytes());
			System.out.println("发送添加节点请求:" + tempNodeId);
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
		try {
			final PathChildrenCache nodeCache = new PathChildrenCache(client, MONITOR_NODES_PATH, true);
			nodeCache.start();
			nodeCache.getListenable().addListener(new PathChildrenCacheListener() {
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					String allocNode = new String(client.getData().forPath(ALLOC_NODE));
					// 只有新添加的加点才进行节点的消费者线程数分配
						switch (event.getType()) {
						case CHILD_ADDED:
							System.out.println("添加新节点:" + event.getData().getPath());
							reAlloctionConsumer();
							break;
						case CHILD_REMOVED:
							System.out.println("删除节点:" + event.getData().getPath());
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
				System.out.println("重新分配后各主机运行的消费者进程数");
				for (String nodeName : nodeConsuemerCounter.keySet()) {
					Integer consumerThreadCount = nodeConsuemerCounter.get(nodeName);
					System.out.println("节点名称:" + nodeName + " 消费者进程数:" + consumerThreadCount);
					client.setData().forPath(MONITOR_NODES_PATH + "/" + nodeName,
							consumerThreadCount.toString().getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void registerDataChangeListener() {
		try {
			final NodeCache nodeCache = new NodeCache(client, MONITOR_NODES_PATH + tempNodeId, false);
			nodeCache.start();
			nodeCache.getListenable().addListener(new NodeCacheListener() {
				public void nodeChanged() throws Exception {
					String newConsumerThreadCountStr = new String(nodeCache.getCurrentData().getData());

					//只有新的节点数发生变化时才执行对应的操作
					if (!oldConsumerThreadCount.equals(newConsumerThreadCountStr)) {

						if (StringUtils.isNumeric(newConsumerThreadCountStr)) {
							System.out.println("该主机上新的消费者进程数为:" + newConsumerThreadCountStr);
							oldConsumerThreadCount = newConsumerThreadCountStr;
						} else {
							System.out.println("该主机上产生了不合法的消费者进程数:" + newConsumerThreadCountStr);
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
