package com.sky.projects.zookeeper.impl;

import com.sky.projects.zookeeper.ZkPath;
import com.sky.projects.zookeeper.ZkRoot;
import com.sky.projects.zookeeper.common.Commons;

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
