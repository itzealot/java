package com.apusic.skynet.zookeeper.impl;

import com.apusic.skynet.zookeeper.ZkPath;
import com.apusic.skynet.zookeeper.ZkRoot;
import com.apusic.skynet.zookeeper.common.Commons;

/**
 * 根节点 ZkRoot
 *
 * @author zt
 *
 */
public final class ZkRootImpl extends ZkPathImpl implements ZkRoot {

	ZkRootImpl(AbstractZkConnection connection) {
		super(connection, Commons.ROOT_PATH);
	}

	@Override
	public ZkPath getParent() {
		return null;
	}

}
