package com.apusic.skynet.curator.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;

/**
 * The ZkWatch
 * 
 * @author zt
 *
 */
public class ZKWatch implements CuratorWatcher {
	private final String path;
	private CuratorFramework zkTools;

	public String getPath() {
		return path;
	}

	public ZKWatch(String path, CuratorFramework zkTools) {
		this.path = path;
		this.zkTools = zkTools;
	}

	@Override
	public void process(WatchedEvent event) throws Exception {
		System.out.println(event.getType());
		if (event.getType() == EventType.NodeDataChanged) {
			byte[] data = zkTools.getData().usingWatcher(this).forPath(path);

			System.out.println(path + ":" + new String(data, CuratorTest.charset));
		}
	}

}