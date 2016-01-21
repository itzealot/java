package com.apusic.skynet.zookeeper.api;

import java.io.Serializable;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The ZkWatch
 * 
 * @author zt
 *
 */
public class ZkWatch<T extends Serializable> implements CuratorWatcher {

	private Logger logger = LoggerFactory.getLogger(ZkConnectionStateListener.class);

	private final ZkPath path;
	private T data;

	public ZkWatch(ZkPath path) {
		this.path = path;
	}

	@Override
	public void process(WatchedEvent event) throws Exception {
		EventType type = event.getType();

		switch (type) {
		case NodeDataChanged:
			logger.info("....................NodeDataChanged....................");

			// 获取数据
			data = path.getData();

			break;

		case NodeChildrenChanged:
			logger.info("....................NodeChildrenChanged....................");

			break;

		case NodeDeleted:
			logger.info("....................NodeDeleted....................");

			break;

		case NodeCreated:
			logger.info("....................NodeCreated....................");

			break;

		default:// None
			break;
		}

		// 设置 Watcher
		path.getConnection().getClient().getData().usingWatcher(this).forPath(path.getPath());
	}

	public T getData() {
		return data;
	}
}