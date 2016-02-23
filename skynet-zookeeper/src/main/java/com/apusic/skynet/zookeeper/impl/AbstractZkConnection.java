package com.apusic.skynet.zookeeper.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;

import com.apusic.skynet.zookeeper.ZkConnection;
import com.apusic.skynet.zookeeper.ZkPath;
import com.apusic.skynet.zookeeper.ZkPath.PathFilter;
import com.apusic.skynet.zookeeper.ZkRoot;

/**
 * AbstractZkConnection
 *
 * @author zt
 *
 */
abstract class AbstractZkConnection implements ZkConnection {

	Object syncObj = new Object();

	boolean transaction = false;
	boolean background = false;
	ZkRoot root = null;

	AbstractZkConnection() {
	}

	protected abstract ZkRoot createRoot();

	public ZkRoot getRoot() {
		if (root == null) {
			synchronized (syncObj) {
				if (root == null)
					root = createRoot();
			}
		}

		return root;
	}

	@Override
	public void beginTransaction() {
		transaction = true;
	}

	@Override
	public boolean inTransaction() {
		return transaction;
	}

	@Override
	public void commitTransaction() {
		transaction = false;
	}

	@Override
	public void setBackground(boolean backgroundMode) {
		background = backgroundMode;
	}

	@Override
	public boolean getBackground() {
		return background;
	}

	/**
	 * 判断是否存在
	 * 
	 * @param path
	 * @return
	 */
	abstract public boolean exists(ZkPathImpl path);

	/**
	 * 删除
	 * 
	 * @param path
	 * @return
	 */
	abstract public boolean delete(ZkPathImpl path);

	/**
	 * 获取孩子节点
	 * 
	 * @param path
	 * @return
	 */
	abstract public List<ZkPath> children(ZkPathImpl path);

	/**
	 * 根据过滤 filter 获取孩子节点
	 * 
	 * @param path
	 * @param filter
	 * @return
	 */
	abstract public List<ZkPath> children(ZkPathImpl path, PathFilter filter);

	/**
	 * 创建单目录
	 * 
	 * @param path
	 * @return
	 */
	abstract public boolean mkdir(ZkPathImpl path);

	/**
	 * 支持创建父目录的同时创建子目录
	 * 
	 * @param path
	 * @return
	 */
	abstract public boolean mkdirs(ZkPathImpl path);

	/**
	 * 获取数据
	 * 
	 * @param path
	 * @return
	 */
	abstract public <T extends Serializable> T getData(ZkPathImpl path);

	/**
	 * 设置数据，不存在则创建
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	abstract public <T extends Serializable> ZkPath setData(ZkPathImpl path, T data);

	abstract CuratorFramework getClient();
}
