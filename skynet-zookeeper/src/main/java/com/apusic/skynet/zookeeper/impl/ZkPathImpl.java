package com.apusic.skynet.zookeeper.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.ProtectACLCreateModePathAndBytesable;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import com.apusic.skynet.zookeeper.ZkPath;
import com.apusic.skynet.zookeeper.ZkRoot;
import com.apusic.skynet.zookeeper.common.Commons;

/**
 * 路径节点 ZkPath
 *
 * @author zt
 *
 */
public class ZkPathImpl implements ZkPath {

	// 基于根的存储路径
	protected String path;

	protected AbstractZkConnection connection;

	ZkPathImpl(AbstractZkConnection connection, String path) {
		checkNotNull(path, "path can not be null");

		this.path = path;
		this.connection = connection;
	}

	@Override
	public String getName() {
		return Commons.dirName(path);
	}

	@Override
	public ZkRoot getRoot() {
		return connection.getRoot();
	}

	@Override
	public ZkPath getParent() {
		String parentPath = Commons.getParentPath(path);
		if (Commons.ROOT_PATH.equals(parentPath)) {
			return getRoot();
		}

		return new ZkPathImpl(connection, parentPath);
	}

	@Override
	public String getParentPath() {
		return getParent().getPath();
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public ZkPath create(String path) {
		if (Commons.ROOT_PATH.equals(path)) {
			return this;
		}

		return this.getChildren(path);
	}

	@Override
	public <T extends Serializable> ZkPath create(String path, T data) {
		return connection.setData(this.getChildren(path), data);
	}

	protected ZkPathImpl getChildren(String path) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path));
	}

	/**
	 * 获取当前对象拼接 path 后的 路径
	 *
	 * @param path
	 * @return
	 */
	protected String getPathBaseOnCurrent(String path) {
		checkNotNull(path, "path can not be null");

		if (path.contains("\\")) {
			path = path.replace('\\', '/');
		}

		String createPath = Commons.BASE;
		char spparator = Commons.SEPARATOR;

		if (path.charAt(0) != spparator) {
			createPath += spparator;
		}

		createPath = createPath + path;

		if (this.isRoot()) {
			return createPath;
		}

		return this.path + createPath;
	}

	public EventType eventType(ZkEventType type) {
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
		throw new IllegalArgumentException();
	}

	private List<ACL> convertACL(ZkACL acl) {
		if (ZkACL.OPEN_ACL_UNSAFE.equals(acl))
			return ZooDefs.Ids.OPEN_ACL_UNSAFE;
		if (ZkACL.CREATOR_ALL_ACL.equals(acl))
			return ZooDefs.Ids.CREATOR_ALL_ACL;
		if (ZkACL.READ_ACL_UNSAFE.equals(acl))
			return ZooDefs.Ids.READ_ACL_UNSAFE;
		throw new IllegalArgumentException();
	}

	@Override
	public ZkPath create(String path, boolean isEphemeral) {

		try {
			if (isEphemeral) {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
						.forPath(getPathBaseOnCurrent(path));
			} else {
				connection.getClient().create().creatingParentsIfNeeded().forPath(getPathBaseOnCurrent(path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getChildren(path);
	}

	@Override
	public <T extends Serializable> ZkPath create(String path, boolean isEphemeral, T data) {
		try {
			if (isEphemeral) {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
						.forPath(getPathBaseOnCurrent(path), Commons.serialize(data));
			} else {
				connection.getClient().create().creatingParentsIfNeeded().forPath(getPathBaseOnCurrent(path),
						Commons.serialize(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getChildren(path);
	}

	@Override
	public <T extends Serializable> ZkPath create(String path, boolean isEphemeral, T data, ZkACL acl) {
		try {
			if (isEphemeral) {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
						.withACL(convertACL(acl)).forPath(getPathBaseOnCurrent(path), Commons.serialize(data));
			} else {
				connection.getClient().create().creatingParentsIfNeeded().withACL(convertACL(acl))
						.forPath(getPathBaseOnCurrent(path), Commons.serialize(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getChildren(path);
	}

	@Override
	public ZkPath createSequential(boolean isEphemeral) {

		try {
			if (isEphemeral) {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
						.forPath(path);
			} else {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL)
						.forPath(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public <T extends Serializable> ZkPath createSequential(boolean isEphemeral, T data) {

		try {
			ProtectACLCreateModePathAndBytesable<String> builder = connection.getClient().create()
					.creatingParentsIfNeeded();
			ACLBackgroundPathAndBytesable<String> builder2;

			if (isEphemeral)
				builder2 = builder.withMode(CreateMode.EPHEMERAL_SEQUENTIAL);
			else
				builder2 = builder;

			builder2.forPath(path, Commons.serialize(data));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public <T extends Serializable> ZkPath createSequential(String path, boolean isEphemeral, T data, ZkACL acl) {

		try {
			if (isEphemeral) {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
						.withACL(convertACL(acl)).forPath(getPathBaseOnCurrent(path), Commons.serialize(data));
			} else {
				connection.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL)
						.withACL(convertACL(acl)).forPath(getPathBaseOnCurrent(path), Commons.serialize(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getChildren(path);
	}

	@Override
	public boolean exists() {
		return connection.exists(this);
	}

	@Override
	public boolean delete() {
		return connection.delete(this);
	}

	@Override
	public List<ZkPath> children() {
		return connection.children(this);
	}

	@Override
	public List<ZkPath> children(PathFilter filter) {
		return connection.children(this, filter);
	}

	@Override
	public boolean mkdir() {
		return connection.mkdir(this);
	}

	@Override
	public boolean mkdirs() {
		return connection.mkdirs(this);
	}

	@Override
	public <T extends Serializable> T getData() {
		return connection.getData(this);
	}

	@Override
	public <T extends Serializable> void setData(T data) {
		connection.setData(this, data);
	}

	@Override
	public boolean isRoot() {
		return false;
	}

}
