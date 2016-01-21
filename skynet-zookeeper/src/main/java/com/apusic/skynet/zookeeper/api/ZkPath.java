package com.apusic.skynet.zookeeper.api;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;

import com.apusic.skynet.zookeeper.ZkConnection;
import com.apusic.skynet.zookeeper.common.Commons;
import com.apusic.skynet.zookeeper.common.ZkConnectionCommons;

/**
 * 路径节点 ZkPath
 * 
 * @author zt
 *
 */
public class ZkPath {

	// 基于根的存储路径
	protected String path;

	// ZkConnction 对象
	protected ZkConnection connection;

	ZkPath(String path) {
		this(path, null);
	}

	ZkPath(String path, ZkConnection connection) {
		this.path = path;
		this.connection = connection;
	}

	/**
	 * To judge the current path is exist
	 * 
	 * 当前 ZkPath 对象是否存在
	 * 
	 * @return
	 */
	public boolean exists() {
		return ZkConnectionCommons.exists(this);
	}

	/**
	 * To judge the path is contained on current
	 * 
	 * 是否存在子路径
	 * 
	 * @param path
	 *            fileName or path
	 * @return
	 */
	public boolean contain(String path) {
		return ZkConnectionCommons.exists(this.get(path));
	}

	/**
	 * To delete the path from current
	 * 
	 * @param path
	 *            fileName or path
	 */
	public void delete(String path) {
		ZkConnectionCommons.delete(this.get(path));
	}

	/**
	 * To delete the current Path object if exist
	 */
	public void delete() {
		ZkConnectionCommons.delete(this);
	}

	/**
	 * To get the current path's data if exist
	 * 
	 * 获取当前 ZkPath 对象的数据
	 * 
	 * @return
	 */
	public <T extends Serializable> T getData() {
		return ZkConnectionCommons.getData(this);
	}

	/**
	 * To get the current path's data if exist
	 * 
	 * 获取当前 ZkPath 对象的数据
	 * 
	 * @return
	 */
	public <T extends Serializable> T getDataUsingWatcher(CuratorWatcher watcher) {
		return ZkConnectionCommons.getDataUsingWatcher(this, watcher);
	}

	/**
	 * To get the path's data base on current Path Object if exist
	 * 
	 * 通过 path 设置数据,基于当前 ZkPath 对象
	 * 
	 * @param path
	 *            fileName or path
	 * 
	 * @return
	 */
	public <T extends Serializable> T getData(String path) {
		return ZkConnectionCommons.getData(this.get(path));
	}

	/**
	 * To get the path's data base on current Path Object if exist
	 * 
	 * 通过 path 设置数据,基于当前 ZkPath 对象
	 * 
	 * @param path
	 *            fileName or path
	 * 
	 * @return
	 */
	public <T extends Serializable> T getDataUsingWatcher(String path, CuratorWatcher watcher) {
		return ZkConnectionCommons.getDataUsingWatcher(this.get(path), watcher);
	}

	/**
	 * To get the current path's data if exist
	 * 
	 * 给 path 设置数据，基于当前对象
	 * 
	 * @param path
	 *            fileName or path
	 * 
	 * @return
	 */
	public <T extends Serializable> void setData(String path, T data) {
		ZkConnectionCommons.setData(this.get(path), data);
	}

	/**
	 * 设置数据
	 * 
	 * @param data
	 */
	public <T extends Serializable> void setData(T data) {
		ZkConnectionCommons.setData(this, data);
	}

	/**
	 * To get the new Path object base on current object
	 * 
	 * 基于当前对象获取新的 ZkPath 对象
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath createPath(String path) {
		return this.get(path);
	}

	/**
	 * To create path by path and object
	 * 
	 * 创建目录并设值
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public <T extends Serializable> ZkPath createAndSet(String path, T data) {
		return ZkConnectionCommons.createAndSet(this.get(path), data);
	}

	/**
	 * To make dir by path base on current Path object
	 * 
	 * 基于当前对象 path 路径创建永久目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath makedir(String path) {
		return ZkConnectionCommons.makedir(this.get(path));
	}

	/**
	 * To create the current path object if not exist
	 * 
	 * 创建永久目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath create() {
		return ZkConnectionCommons.makedir(this);
	}

	/**
	 * To create the current path object if not exist
	 * 
	 * 创建临时目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath createEphemeral() {
		return ZkConnectionCommons.createWithMode(this, CreateMode.EPHEMERAL);
	}

	/**
	 * To create the current path object if not exist
	 * 
	 * 创建自动编号的临时目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath createEphemeralAndSequemtial() {
		return ZkConnectionCommons.createWithMode(this, CreateMode.EPHEMERAL_SEQUENTIAL);
	}

	/**
	 * To create the current path object if not exist
	 * 
	 * 创建后缀带自动编号的永久目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath createPersistentAndSequemtial() {
		return ZkConnectionCommons.createWithMode(this, CreateMode.PERSISTENT_SEQUENTIAL);
	}

	/**
	 * To create the current path object if not exist
	 * 
	 * 创建 ALC 的永久目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath createWithACL(List<ACL> aclList) {
		return ZkConnectionCommons.createWithACL(this, aclList);
	}

	/**
	 * To create the current path object if not exist
	 * 
	 * 创建 ALC 的永久目录
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath createWithModeAndACL(CreateMode mode, List<ACL> aclList) {
		return ZkConnectionCommons.createWithModeAndACL(this, mode, aclList);
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
			throw new RuntimeException("path can not have '\' char ");
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

	/**
	 * 是否含有孩子节点
	 * 
	 * @return
	 */
	public boolean hasChilren() {
		return getChildrenCount() != 0;
	}

	/**
	 * To get the all children base on current Path object
	 * 
	 * 获取当前 ZkPath 对象下 path 路径中的子目录名称列表
	 * 
	 * @param path
	 * @return
	 */
	public List<String> children(String path) {
		return ZkConnectionCommons.children(this.get(path));
	}

	/**
	 * To get the Object's all children
	 * 
	 * 获取当前 ZkPath 对象的子目录名称列表
	 * 
	 * @param path
	 * @return
	 */
	public List<String> children() {
		return ZkConnectionCommons.children(this);
	}

	/**
	 * 获取直接孩子节点的数量
	 * 
	 * @return
	 */
	public int getChildrenCount() {
		return this.children().size();
	}

	/**
	 * 返回存储路径
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * To get the ZkPath Object by path
	 * 
	 * 根据路径获取 ZkPath 对象，基于当前 ZkPath 对象
	 * 
	 * @param path
	 * @return
	 */
	public ZkPath get(String path) {
		return new ZkPath(getPathBaseOnCurrent(path), connection);
	}

	/**
	 * To get the parent's path
	 * 
	 * 获取父路径
	 * 
	 * @return
	 */
	public String getParentPath() {
		if (this.isRoot()) {
			return null;
		}

		int index = path.lastIndexOf('/');

		if (index == -1 || index == 0) {
			return Commons.ROOT_PATH;
		}
		return path.substring(0, index);
	}

	/**
	 * To get the parent's Path object
	 * 
	 * 获取父节点
	 * 
	 * @return
	 */
	public ZkPath parent() {
		if (isRoot()) {
			return null;
		}

		String parent = getParentPath();
		if (Commons.ROOT_PATH.equals(parent)) {
			return this.connection.getRoot();
		}
		return new ZkPath(parent, this.connection);
	}

	/**
	 * 是否是根节点
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return Commons.ROOT_PATH.equals(path);
	}

	@Override
	public String toString() {
		return "ZkPath [path=" + path + "]";
	}

	public ZkConnection getConnection() {
		return connection;
	}
}
