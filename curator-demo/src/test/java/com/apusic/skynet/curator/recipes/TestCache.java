package com.apusic.skynet.curator.recipes;

import java.io.IOException;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import com.apusic.skynet.curator.ClientFactory;

public class TestCache {
	public static PathChildrenCache pathChildrenCache(CuratorFramework client, String path, Boolean cacheData)
			throws Exception {
		final PathChildrenCache cached = new PathChildrenCache(client, path, cacheData);

		PathChildrenCacheListener listener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				PathChildrenCacheEvent.Type eventType = event.getType();

				switch (eventType) {
				case CONNECTION_RECONNECTED:
					// rebuild the cache
					cached.rebuild();
					break;

				case CONNECTION_SUSPENDED:
				case CONNECTION_LOST:
					System.out.println("Connection error,waiting...");
					break;

				default:
					System.out.println("Data: " + event.getData().toString());
				}
			}
		};

		cached.getListenable().addListener(listener);
		return cached;
	}

	public static void main(String[] args) throws Exception {
		CuratorFramework client = ClientFactory.newClient();
		client.start();

		String path = "/testCache";
		client.create().forPath(path, path.getBytes());

		PathChildrenCache cached = pathChildrenCache(client, path, true);
		// = start() + rebuild(), 事务操作,将会在额外的线程中执行.
		cached.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);


		String child = path + "/child";
		client.create().forPath(child, child.getBytes());

		// 将从获取本地的数据列表,而不是触发一次zookeeper.getChildren(),因此为"Cache".
		List<ChildData> childData = cached.getCurrentData();

		if (childData != null) {
			for (ChildData data : childData) {
				System.out.println("Path:" + data.getPath() + ",data" + new String(data.getData()));
			}
		}

		client.delete().forPath(child);
		client.delete().forPath(path);

	}

	public static void close(PathChildrenCache cached) {
		if (cached == null)
			return;

		// 当不在需要关注此节点数据时,需要及时的关闭它.因为每个cached,都会额外的消耗一个线程.
		// close the watcher,clear the cached Data
		try {
			cached.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
