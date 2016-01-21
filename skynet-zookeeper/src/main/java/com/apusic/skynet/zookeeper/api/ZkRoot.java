package com.apusic.skynet.zookeeper.api;

import static com.google.common.base.Preconditions.checkNotNull;

import com.apusic.skynet.zookeeper.ZkConnection;
import com.apusic.skynet.zookeeper.common.Commons;

/**
 * 根节点 ZkRoot
 * 
 * @author zt
 *
 */
public final class ZkRoot extends ZkPath {

	// ZkRoot 采用单例模式实现
	private static ZkRoot instance = null;

	ZkRoot(ZkConnection connection) {
		super(Commons.ROOT_PATH, connection);
	}

	/**
	 * 获取 ZkRoot 对象实例，单例模式实现
	 * 
	 * @return
	 */
	public static synchronized ZkRoot getInstance(ZkConnection connection) {
		checkNotNull("ZkConnection object is null", connection);

		if (instance == null) {
			instance = new ZkRoot(connection);
		}

		return instance;
	}

	@Override
	public ZkPath create() {
		return instance;
	}

	@Override
	public String getParentPath() {
		return null;
	}

	@Override
	public ZkPath parent() {
		return null;
	}

	@Override
	public boolean isRoot() {
		return true;
	}

}
