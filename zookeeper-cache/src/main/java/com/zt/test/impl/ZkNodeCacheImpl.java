package com.zt.test.impl;

import java.util.Collection;

import com.zt.test.ZkNodeCachable;
import com.zt.test.entity.Node;

/**
 * 节点缓存实现类
 * 
 * @author zt
 *
 */
public class ZkNodeCacheImpl extends ZkCacheImpl<String, Node> implements ZkNodeCachable {

	private static ZkNodeCacheImpl instance = null;

	private ZkNodeCacheImpl() {
	}

	/**
	 * 获取节点缓存的实例
	 * 
	 * @return
	 */
	public static ZkNodeCacheImpl getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new ZkNodeCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public Node getParent(Node node) {
		return null;
	}

	@Override
	public Collection<Node> getChildren(Node node) {
		return null;
	}

}
