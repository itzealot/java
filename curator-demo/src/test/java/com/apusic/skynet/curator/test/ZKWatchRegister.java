package com.apusic.skynet.curator.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

public class ZKWatchRegister implements CuratorWatcher {
	private final String path;
	private byte[] value;
	private CuratorFramework zkTools;

	public String getPath() {
		return path;
	}

	public ZKWatchRegister(String path, byte[] value, CuratorFramework zkTools) {
		this.path = path;
		this.value = value;
		this.zkTools = zkTools;
	}

	@Override
	public void process(WatchedEvent event) throws Exception {
		System.out.println(event.getType());

		if (event.getType() == EventType.NodeDataChanged) {
			// 节点数据改变了，需要记录下来，以便session过期后，能够恢复到先前的数据状态
			byte[] data = zkTools.getData().usingWatcher(this).forPath(path);
			value = data;

			System.out.println(path + ":" + new String(data, CuratorTest.charset));
		} else if (event.getType() == EventType.NodeDeleted) {
			// 节点被删除了，需要创建新的节点
			System.out.println(path + ":" + path + " has been deleted.");

			Stat stat = zkTools.checkExists().usingWatcher(this).forPath(path);
			if (stat == null) {
				zkTools.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
						.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path);
			}
		} else if (event.getType() == EventType.NodeCreated) {
			// 节点被创建时，需要添加监听事件（创建可能是由于session过期后，curator的状态监听部分触发的）
			System.out.println(path + ":" + " has been created!" + "the current data is " + new String(value));

			zkTools.setData().forPath(path, value);
			zkTools.getData().usingWatcher(this).forPath(path);
		}
	}
}