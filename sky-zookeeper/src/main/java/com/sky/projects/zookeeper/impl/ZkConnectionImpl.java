package com.sky.projects.zookeeper.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import com.google.common.collect.Lists;
import com.sky.projects.zookeeper.ZkPath;
import com.sky.projects.zookeeper.ZkPath.PathFilter;
import com.sky.projects.zookeeper.ZkPath.ZkACL;
import com.sky.projects.zookeeper.ZkPath.ZkEventType;
import com.sky.projects.zookeeper.ZkRoot;
import com.sky.projects.zookeeper.common.Commons;

/**
 * ZkConnection
 *
 * @author zt
 *
 */
public final class ZkConnectionImpl extends AbstractZkConnection {

	private static final byte[] EMPTY_BYTES = new byte[0];
	CuratorFramework client;

	public ZkConnectionImpl(CuratorFramework client) {
		this.client = client;
	}

	@Override
	public void close() {
		if (client != null) {
			client.close();
		}
	}

	@Override
	public ZkRoot createRoot() {
		return new ZkRootImpl(this);
	}

	@Override
	public boolean exists(ZkPathImpl path) {
		try {
			if (path.watch == null)
				return client.checkExists().forPath(path.getPath()) != null;
			else
				return client.checkExists().usingWatcher(path.hook).forPath(path.getPath()) != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(ZkPathImpl path) {

		if (!exists(path)) {
			return false;
		}

		try {
			client.delete().forPath(path.getPath());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ZkPath> children(ZkPathImpl path) {
		return children(path, null);
	}

	@Override
	public List<ZkPath> children(ZkPathImpl path, PathFilter filter) {
		List<String> childrens = null;

		try {
			if (path.watch == null)
				childrens = client.getChildren().forPath(path.getPath());
			else
				childrens = client.getChildren().usingWatcher(path.hook).forPath(path.getPath());
		} catch (Exception e) {
			// TODO
			e.printStackTrace();

			return Lists.newArrayList();
		}

		String base = path.getPath();
		if (Commons.ROOT_PATH.equals(base)) {
			base = Commons.BASE;
		}

		List<ZkPath> paths = Lists.newArrayList();

		for (String str : childrens) {
			String p = base + Commons.ROOT_PATH + str;
			ZkPath zkPath = new ZkPathImpl(this, p);

			if (filter == null || filter.accpet(p)) {
				paths.add(zkPath);
			}
		}

		return paths;
	}

	static EventType eventType(ZkEventType type) {
		if (ZkEventType.None.equals(type))
			return Watcher.Event.EventType.None;
		if (ZkEventType.NodeCreated.equals(type))
			return Watcher.Event.EventType.NodeCreated;
		if (ZkEventType.NodeDeleted.equals(type))
			return Watcher.Event.EventType.NodeDeleted;
		if (ZkEventType.NodeDataChanged.equals(type))
			return Watcher.Event.EventType.NodeDataChanged;
		if (ZkEventType.NodeChildrenChanged.equals(type))
			return Watcher.Event.EventType.NodeChildrenChanged;

		return Watcher.Event.EventType.None;
	}

	static ZkEventType fromEventType(EventType type) {
		if (Watcher.Event.EventType.None.equals(type))
			return ZkEventType.None;
		if (Watcher.Event.EventType.NodeCreated.equals(type))
			return ZkEventType.NodeCreated;
		if (Watcher.Event.EventType.NodeDeleted.equals(type))
			return ZkEventType.NodeDeleted;
		if (Watcher.Event.EventType.NodeDataChanged.equals(type))
			return ZkEventType.NodeDataChanged;
		if (Watcher.Event.EventType.NodeChildrenChanged.equals(type))
			return ZkEventType.NodeChildrenChanged;

		return ZkEventType.None;
	}

	static List<ACL> convertACL(ZkACL acl) {
		if (ZkACL.OPEN_ACL_UNSAFE.equals(acl))
			return ZooDefs.Ids.OPEN_ACL_UNSAFE;
		if (ZkACL.CREATOR_ALL_ACL.equals(acl))
			return ZooDefs.Ids.CREATOR_ALL_ACL;
		if (ZkACL.READ_ACL_UNSAFE.equals(acl))
			return ZooDefs.Ids.READ_ACL_UNSAFE;

		return null;
	}

	@Override
	public boolean mkdir(ZkPathImpl path) {
		try {
			Serializable data = path.getData();
			byte[] bytes = data == null ? EMPTY_BYTES : Commons.serialize(data);

			String result = client.create().withMode(getMode(path)).withACL(convertACL(path.getAcl()))
					.forPath(path.getPath(), bytes);

			// 修改新的 path 为生成的 path
			path.path = result;

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean mkdirs(ZkPathImpl path) {
		// 预处理 ACL
		List<ACL> aclList = convertACL(path.getAcl());

		// 预处理 CreateMode
		CreateMode mode = getMode(path);

		try {
			Serializable data = path.getData();
			byte[] bytes = data == null ? EMPTY_BYTES : Commons.serialize(data);

			String result = client.create().creatingParentsIfNeeded().withMode(mode).withACL(aclList)
					.forPath(path.getPath(), bytes);

			// 修改新的 path 为生成的 path
			path.path = result;

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 从 ZkPathImpl 对象中解析出 CreateMode
	 *
	 * @param path
	 * @return
	 */
	private CreateMode getMode(ZkPathImpl path) {
		// 预处理 CreateMode
		CreateMode mode = null;
		if (path.sequential) {
			if (path.ephemeral) {
				mode = CreateMode.EPHEMERAL_SEQUENTIAL;
			} else {
				mode = CreateMode.PERSISTENT_SEQUENTIAL;
			}
		} else {
			if (path.ephemeral) {
				mode = CreateMode.EPHEMERAL;
			} else {
				mode = CreateMode.PERSISTENT;
			}
		}
		return mode;
	}

	@Override
	public <T extends Serializable> T getData(ZkPathImpl path) {
		if (!exists(path)) {
			return null;
		}

		try {
			if (path.watch == null)
				return Commons.readFrom(client.getData().forPath(path.getPath()));

			return Commons.readFrom(client.getData().usingWatcher(path.hook).forPath(path.getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public <T extends Serializable> ZkPath setData(ZkPathImpl path, T data) {
		// 不存在则先创建
		if (!exists(path)) {
			mkdirs(path);
		}

		try {
			byte[] bytes = data == null ? EMPTY_BYTES : Commons.serialize(data);
			client.setData().forPath(path.getPath(), bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ZkPathImpl(this, path.getPath());
	}

}
