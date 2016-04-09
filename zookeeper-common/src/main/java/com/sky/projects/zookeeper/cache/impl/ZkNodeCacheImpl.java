package com.sky.projects.zookeeper.cache.impl;

import java.util.Collection;

import com.sky.projects.zookeeper.cache.ZkNodeCachable;
import com.sky.projects.zookeeper.entity.Node;

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
