package com.projects.sky.zookeeper.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.common.PathConst;
import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.listener.MonitorCallBack;
import com.projects.sky.zookeeper.monitor.impl.CommonMonitorImpl;
import com.projects.sky.zookeeper.register.CommonRegister;
import com.projects.sky.zookeeper.register.impl.CommonRegisterImpl;

/**
 * Created by a on 2015/12/1.
 */
public class NodeMonitor implements MonitorCallBack {
	private final Logger log = LoggerFactory.getLogger(NodeMonitor.class);

	private ExecutorService pool;

	private CommonMonitor commonMonitor;

	private CommonRegister commonService;

	private Map<String, Node> nodeCache;

	public NodeMonitor(Map<String, Node> nodeCache) {
		log.info("====new node monitor===");
		this.nodeCache = nodeCache;
		pool = Executors.newCachedThreadPool();
		commonMonitor = new CommonMonitorImpl(pool);
		commonService = new CommonRegisterImpl();
		createNodeChildrenMonitor();
		createNodeDataMonitor();
	}

	private void createNodeChildrenMonitor() {
		log.info("====create node children monitor===");
		commonMonitor.createChilrendMoniotr(this, PathConst.NODE_IDX);
	}

	private void createNodeDataMonitor() {
		log.info("====create node data monitor===");
		commonMonitor.createDataMonitor(this, PathConst.NODE_IDX);
	}

	@Override
	public void monitorChildren(String path, List<String> nodes) {
		List<String> keys = new ArrayList<>(nodeCache.keySet());
		if (keys.size() == nodes.size()) {
			log.info("=========no need update cache=========");
			return;
		} else if (keys.size() > nodes.size()) {
			if (keys.removeAll(nodes)) {
				for (String key : keys) {
					log.info("=========remove node from cache {}=========", key);
					nodeCache.remove(key);
				}
			}
		} else {
			if ((keys.size() == 0) || nodes.removeAll(keys)) {
				for (String code : nodes) {
					String nodePath = Commons.getPathByCode(code);
					Node node = commonService.<Node> getData(PathConst.ORG,
							"/", nodePath, "/N");
					log.info("=========add node to cache {}=========", node);
					nodeCache.put(code, node);
				}
			}
		}
	}

	@Override
	public void monitorData(String path, byte[] data) {
		String code = Commons.<String> deserialize(data);
		if ("null".equals(code)) {
			return;
		}

		String orgPath = Commons.getPathByCode(code);
		Node node = commonService.<Node> getData(PathConst.ORG, "/", orgPath);
		nodeCache.put(code, node);
		log.info("=========Update node cache {}=========", node);
	}

	@Override
	public void monitorExists(String path) {
		return;
	}

}
