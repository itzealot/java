package com.sky.projects.zookeeper.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import com.sky.projects.zookeeper.ZkPath;
import com.sky.projects.zookeeper.ZkRoot;
import com.sky.projects.zookeeper.common.Commons;

/**
 * 路径节点 ZkPath
 *
 * @author zt
 *
 */
public class ZkPathImpl implements ZkPath {

	private static final String DEFAULT_DIR_NAME = "/aip-default-name-";

	// 是否是临时节点标记
	boolean ephemeral = false;

	// 是否带序列标识
	boolean sequential = false;

	// ACL
	ZkACL acl = null;

	/**
	 * 用于外部回调
	 */
	ZkWatch watch = null;

	/**
	 * 用于和zk挂接的链接程序
	 */
	CuratorWatcher hook = new CuratorWatcher() {

		@Override
		public void process(WatchedEvent event) throws Exception {
			if (watch == null)
				return;

			ZkPathImpl evPath = new ZkPathImpl(ZkPathImpl.this.connection, event.getPath());
			// if( ZkPathImpl.this.path.equals(pp) )
			watch.accept(ZkConnectionImpl.fromEventType(event.getType()), evPath,
					event.getState() == KeeperState.SyncConnected);
		}

	};

	// 数据
	private Serializable data;

	// 基于根的存储路径
	protected String path;

	protected AbstractZkConnection connection;

	ZkPathImpl(AbstractZkConnection connection, String path, Serializable data, boolean ephemeral, boolean sequential,
			ZkACL acl) {
		this.connection = connection;
		this.path = path;
		this.data = data;
		this.acl = acl;
		this.ephemeral = ephemeral;
		this.sequential = sequential;
	}

	ZkPathImpl(AbstractZkConnection connection, String path) {
		this(connection, path, null, false, false, null);
	}

	@Override
	public String getName() {
		String source = this.path;
		int index = source.lastIndexOf(Commons.ROOT_PATH);
		return source.substring(index + 1);
	}

	@Override
	public ZkRoot getRoot() {
		return connection.getRoot();
	}

	@Override
	public ZkPath getParent() {
		String parentPath = getParentPath();

		if (Commons.ROOT_PATH.equals(parentPath)) {
			return this.getRoot();
		}

		return new ZkPathImpl(connection, parentPath);
	}

	/**
	 * 获取父路径
	 *
	 * @return
	 */
	@Override
	public String getParentPath() {
		int index = this.path.lastIndexOf(Commons.ROOT_PATH);

		if (index == 0) {
			return Commons.ROOT_PATH;
		}

		return this.path.substring(index);
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

		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path));
	}

	@Override
	public <T extends Serializable> ZkPath create(String path, T data) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path), data, false, false, null);
	}

	/**
	 * 获取当前对象拼接 path 后的 路径
	 *
	 * @param path
	 * @return
	 */
	private String getPathBaseOnCurrent(String path) {
		Commons.checkPath(path);

		if (Commons.ROOT_PATH.equals(this.path)) {
			return path;
		}

		return this.path + path;
	}

	@Override
	public ZkPath create(String path, boolean isEphemeral) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path), null, isEphemeral, false, null);
	}

	@Override
	public <T extends Serializable> ZkPath create(String path, boolean isEphemeral, T data) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path), data, isEphemeral, false, null);
	}

	@Override
	public <T extends Serializable> ZkPath create(String path, boolean isEphemeral, T data, ZkACL acl) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path), data, isEphemeral, false, acl);
	}

	@Override
	public ZkPath createSequential(boolean isEphemeral) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(DEFAULT_DIR_NAME), null, isEphemeral, true, null);
	}

	@Override
	public <T extends Serializable> ZkPath createSequential(boolean isEphemeral, T data) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(DEFAULT_DIR_NAME), data, isEphemeral, true, null);
	}

	@Override
	public <T extends Serializable> ZkPath createSequential(String path, boolean isEphemeral, T data, ZkACL acl) {
		return new ZkPathImpl(connection, this.getPathBaseOnCurrent(path), data, isEphemeral, true, acl);
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

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T getData() {
		return (T) data;
	}

	@Override
	public void loadData() {
		this.data = connection.getData(this);
	}

	@Override
	public void saveData() {
		connection.setData(this, data);
	}

	@Override
	public <T extends Serializable> void setData(T data) {
		this.data = data;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	public ZkACL getAcl() {
		return acl;
	}

	/**
	 * 设置watch之后，必须调用一下 {@code #children()}或{@code #exists()}或
	 * {@code #getData()}，回调才能生效。
	 * 
	 * @param watch
	 */
	@Override
	public void setWatch(ZkWatch watch) {
		this.watch = watch;
	}

	@Override
	public ZkWatch getWatch() {
		return watch;
	}
}
