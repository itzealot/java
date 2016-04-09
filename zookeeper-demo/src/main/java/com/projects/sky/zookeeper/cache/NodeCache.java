package com.projects.sky.zookeeper.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.listener.CacheListener;
import com.projects.sky.zookeeper.monitor.NodeMonitor;
import com.projects.sky.zookeeper.register.NodeRegister;

/**
 * NodeCache
 * 
 * @author zt
 * @date 2016年1月5日
 */
public class NodeCache {

	private final Logger log = LoggerFactory.getLogger(NodeCache.class);

	// NodeCache singleton
	private static NodeCache cache = null;

	// Node Cache
	private Map<String, Node> nodes = new LinkedHashMap<>();;

	// The Node's CacheListener
	private CacheListener<Node> listener;

	private NodeCache() {
		log.info("===== new node cache ==== {}", this);

		// listener
		listener = new NodeRegister();

		// new NodeMonitor
		new NodeMonitor(nodes);

		// init Map<String, Node>
		init(nodes);
	}

	public static synchronized NodeCache getNodeCache() {
		if (cache == null) {
			cache = new NodeCache();
		}
		return cache;
	}

	/**
	 * 系统启动时，初始化zookeeper
	 */
	private void init(Map<String, Node> cache) {
		listener.init(cache);
	}

	/**
	 * 获取所有节点
	 * 
	 * @return
	 */
	public Collection<Node> getAll() {
		return nodes.values();
	}

	public void add(Node node) {
		nodes.put(node.getCode(), node);
		listener.remoteAdd(node);
	}

	public void update(Node node) {
		nodes.put(node.getCode(), node);
		listener.remoteUpdate(node);
	}

	public void delete(Node node) {
		nodes.remove(node.getCode());
		listener.remoteDelete(node);
	}

	public Node get(String code) {
		return nodes.get(code);
	}

	/**
	 * 获取下级节点
	 * 
	 * @param node
	 * @return
	 */
	public Collection<Node> getChildren(Node node) {
		String parent = node.getCode();
		Collection<Node> children = new ArrayList<Node>();
		for (String code : nodes.keySet()) {
			if (!code.equals(parent) && code.startsWith(parent)) {
				children.add(nodes.get(code));
			}
		}
		return children;
	}

	/**
	 * 获取上级节点
	 * 
	 * @param node
	 * @return
	 */
	public Node getParent(Node node) {
		String code = node.getCode();
		String parent = code.substring(0, code.lastIndexOf("C") - 1);
		return nodes.get(parent);
	}

}
