package com.apusic.skynet.curator.watch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooDefs.Perms;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CuratorFrameworkTest {

	private Logger logger = LoggerFactory.getLogger(CuratorFrameworkTest.class);

	// more Zookeepers using char ',' split
	// private final String url = "10.1.1.35:2181,10.1.1.35:2281";
	private final String url = "localhost:2181";
	private final int connectionTimeoutMs = 10 * 1000;
	private final int sleepMsBetweenRetries = 5000;
	private final int sessionTimeoutMs = 20 * 1000;
	private final String path = "/app";
	private final boolean cacheData = true;
	private CuratorFramework client;

	@Test
	public void startup() {
		final CountDownLatch counter = new CountDownLatch(1);

		// Connection State Listener
		ConnectionStateListener connectionStateListener = new ConnectionStateListener() {
			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {

				// 连接状改变
				if (newState == ConnectionState.CONNECTED) {
					logger.info("state:{}", ConnectionState.CONNECTED);

					counter.countDown();
					System.out.println(ConnectionState.CONNECTED);
				}
			}
		};

		client.getConnectionStateListenable().addListener(connectionStateListener);
		client.start();

		try {
			// 其他的等待
			counter.await();
		} catch (InterruptedException e) {
			logger.error("", e);
		}

		// 移除监听器
		client.getConnectionStateListenable().removeListener(connectionStateListener);

		String fullPath = ZKPaths.makePath(path, "/4"); // 监听节点/app/4的变化
		final PathChildrenCache childrenCache = new PathChildrenCache(client, fullPath, cacheData);

		// Path Children Cache Listener
		PathChildrenCacheListener listener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				logger.info("client:{}, event:{}", client, event);

				List<ChildData> currentData = childrenCache.getCurrentData();

				for (ChildData childData : currentData) {

					logger.info("path:{}, data:{}", childData.getPath(), new String(childData.getData()));
					// System.out.println("path: " + path);
					// System.out.println("data: " + new
					// String(childData.getData()));
				}
			}
		};

		childrenCache.getListenable().addListener(listener);

		try {
			childrenCache.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String command;
		try {
			while ((command = reader.readLine()) != null) {
				System.out.println("command: " + command);
				if (command.equalsIgnoreCase("quit")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("finashed!...........................................");
	}

	@Before
	public void init() throws NoSuchAlgorithmException {
		Builder builder = CuratorFrameworkFactory.builder().connectString(url).connectionTimeoutMs(connectionTimeoutMs);
		// 设定重试策略
		builder = builder.retryPolicy(new RetryNTimes(Integer.MAX_VALUE, sleepMsBetweenRetries))
				.sessionTimeoutMs(sessionTimeoutMs);

		ArrayList<ZkConf> aclConf = Lists.newArrayList(new ZkConf("/app", "u1001", "p1001", Perms.READ),
				new ZkConf("/app/4", "u1001", "p1001", Perms.ALL));

		ACLProvider aclProvider = new PathDefaultACLProvider(aclConf);
		client = builder.aclProvider(aclProvider).build();
	}

	@After
	public void destory() {
		logger.info("destory.............................................");
		if (client != null) {
			client.close();
		}
	}

}