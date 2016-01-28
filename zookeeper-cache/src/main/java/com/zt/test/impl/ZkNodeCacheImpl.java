package com.zt.test.impl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.zt.test.ZkCachable;
import com.zt.test.ZkNodeCachable;
import com.zt.test.entity.Node;

/**
 * 节点缓存实现类
 * 
 * @author zt
 *
 */
public class ZkNodeCacheImpl implements ZkNodeCachable {

	private static ZkCachable<String, Node> nodes = null;

	private static ZkNodeCachable instance = null;

	private static Object lock = new Object();

	private ZkNodeCacheImpl() {
	}

	/**
	 * 获取节点缓存的实例对象
	 * 
	 * @return
	 */
	public static ZkNodeCachable getInstance() {
		if (instance == null) {
			synchronized (lock) {
				nodes = new ZkCacheImpl<String, Node>();

				instance = new ZkNodeCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public Node delete(String key) {
		return nodes.delete(key);
	}

	@Override
	public Node update(String key, Node value) {
		return nodes.update(key, value);
	}

	@Override
	public void add(String key, Node value) {
		nodes.add(key, value);
	}

	@Override
	public Node get(String key) {
		return nodes.get(key);
	}

	@Override
	public Collection<Node> getAll() {
		return nodes.getAll();
	}

	@Override
	public void init(ConcurrentHashMap<String, Node> cache) {
		nodes.init(cache);
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
