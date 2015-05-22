package com.eric.hadoop.zookeeper;

import java.io.File;
import java.util.Date;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.apache.curator.test.TestingZooKeeperServer;
import org.apache.curator.utils.EnsurePath;

public class CuratorToolsTest {
	private static final String IPS = "localhost:2181";
	private static final int SESSION_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 5000;
	private static final String NAMESPACE = "curatortest";
	private static CuratorFramework client;
	
	public static void main(String[] args) {
		intClient();
		testDemonstrateCluster();
		
	}
	
	public static void intClient(){
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.builder().connectString(IPS)
				.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
				.namespace(NAMESPACE).build();
		client.start();
	}
	
	
	
	//测试TestingServer
		public static void testDemonstrateCluster(){
			try {
				String testPath="/test";
				TestingCluster testServer=new TestingCluster(3);
				testServer.start();
				//等待集群启动好
				Thread.sleep(2000);
				for(TestingZooKeeperServer testingZooKeeperServer:testServer.getServers()){
					System.out.println("ServerId:"+testingZooKeeperServer.getInstanceSpec().getServerId());
					System.out.println("ElectionType:"+testingZooKeeperServer.getQuorumPeer().getElectionType());
					System.out.println("ServerState:"+testingZooKeeperServer.getQuorumPeer().getServerState());
				}
				
				RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
				CuratorFramework testServerClient = CuratorFrameworkFactory.builder().connectString(testServer.getConnectString())
						.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
						.build();
				testServerClient.start();
				EnsurePath ensurePath=new EnsurePath(testPath);
				ensurePath.ensure(testServerClient.getZookeeperClient());
				testServerClient.setData().forPath(testPath,new Date().toString().getBytes());
				System.out.println(new String(testServerClient.getData().forPath(testPath)));
				Thread.sleep(2000);
				testServer.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	//测试TestingServer
	public static void testDemonstrateServer(){
		try {
			String testPath="/test";
			TestingServer testServer=new TestingServer(2888, new File("./demoserver_data"));
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			CuratorFramework testServerClient = CuratorFrameworkFactory.builder().connectString(testServer.getConnectString())
					.sessionTimeoutMs(SESSION_TIMEOUT).connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(retryPolicy)
					.build();
			testServerClient.start();
			EnsurePath ensurePath=new EnsurePath(testPath);
			ensurePath.ensure(testServerClient.getZookeeperClient());
			testServerClient.setData().forPath(testPath,new Date().toString().getBytes());
			System.out.println(new String(testServerClient.getData().forPath(testPath)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//测试EnsurePath功能
	public static void testEnsurePath(){
		
		String testPath="/testensurenode";
		//此处路径指的是完整路径，NAMESPACE的设置在这里需要手动添加
		EnsurePath ensurePath=new EnsurePath("/"+NAMESPACE+testPath);
		try {
			ensurePath.ensure(client.getZookeeperClient());
			System.out.println(client.getData().forPath(testPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
