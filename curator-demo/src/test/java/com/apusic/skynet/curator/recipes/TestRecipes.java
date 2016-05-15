package com.apusic.skynet.curator.recipes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRecipes {

	CuratorFramework client = null;
	// 在注册监听器的时候，如果传入此参数，当事件触发时，逻辑由线程池处理
	ExecutorService pool = null;

	@Before
	public void before() throws Exception {
		pool = Executors.newFixedThreadPool(1);

		client = CuratorFrameworkFactory.builder().connectString("localhost:2181").sessionTimeoutMs(5000)
				.connectionTimeoutMs(3000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();

		client.delete().deletingChildrenIfNeeded().forPath("/zk-huey");
	}

	@After
	public void after() throws Exception {
		pool.shutdown();
		client.close();
	}

	@Test
	public void testNodeCache() throws Exception {
		String path = "/zk-huey/cnode";
		client.create().creatingParentsIfNeeded().forPath(path, "hello".getBytes());

		// 监听数据节点的变化情况
		final NodeCache nodeCache = new NodeCache(client, path, false);
		nodeCache.start(true);

		nodeCache.getListenable().addListener(getNodeCacheListener(nodeCache), pool);

		client.setData().forPath(path, path.getBytes());

		Thread.sleep(10 * 1000);
		nodeCache.close();
	}

	private NodeCacheListener getNodeCacheListener(final NodeCache nodeCache) {
		return new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				System.out.println("............................................");

				byte[] array = nodeCache.getCurrentData().getData();

				System.out.println("Node data is changed, new data: " + new String(array));
				System.out.println("............................................");
			}
		};
	}

	@Test
	public void testPathChildrenCacheListener() throws Exception {
		String path = "/zk-huey";

		// 监听子节点的变化情况
		final PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
		childrenCache.start(StartMode.POST_INITIALIZED_EVENT);

		childrenCache.getListenable().addListener(getPathChildrenCacheListener(), pool);

		String pathChild = "/zk-huey/cnode";
		client.setData().forPath(pathChild, "world".getBytes());

		Thread.sleep(10 * 1000);
		childrenCache.close();
	}

	private PathChildrenCacheListener getPathChildrenCacheListener() {
		return new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				System.out.println("PathChildrenCacheListener...............................");

				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED: " + event.getData().getPath());
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED: " + event.getData().getPath());
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED: " + event.getData().getPath());
					break;
				default:
					break;
				}
			}
		};
	}
}