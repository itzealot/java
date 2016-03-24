package com.projects.sky.zookeeper.impl;

import com.projects.sky.zookeeper.ZkPath;
import com.projects.sky.zookeeper.ZkRoot;
import com.projects.sky.zookeeper.common.Commons;

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
