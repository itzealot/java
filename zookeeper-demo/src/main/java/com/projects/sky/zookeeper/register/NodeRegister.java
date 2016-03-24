package com.projects.sky.zookeeper.register;

import java.util.List;
import java.util.Map;

import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.common.PathConst;
import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.entity.Node;
import com.projects.sky.zookeeper.listener.CacheListener;
import com.projects.sky.zookeeper.register.impl.CommonRegisterImpl;

/**
 * Created by Colin on 2015/12/1.
 */
public class NodeRegister implements CacheListener<Node> {

	private final Logger log = LoggerFactory.getLogger(NodeRegister.class);

	private CommonRegister commonService;

	public NodeRegister() {
		commonService = new CommonRegisterImpl();
	}

	@Override
	public void remoteDelete(Node node) {
		String code = node.getCode();
		String path = Commons.getPathByCode(code);
		commonService.delete(PathConst.ORG, "/", path, "/N/S");
		commonService.delete(PathConst.ORG, "/", path, "/N/M");
		commonService.delete(PathConst.ORG, "/", path, "/N");
		commonService.delete(PathConst.NODE_IDX, "/", code);
		log.info("delete node from zookeeper======>>{}", node);
	}

	@Override
	public void remoteUpdate(Node node) {
		String code = node.getCode();
		String path = Commons.getPathByCode(code);
		commonService.setData(node, PathConst.ORG, "/", path, "/N");
		commonService.setData(code, PathConst.NODE_IDX);
		log.info("update node to zookeeper======>>{}", node);
	}

	@Override
	public void remoteAdd(Node node) {
		String code = node.getCode();
		String path = Commons.getPathByCode(code);
		commonService.create(node, CreateMode.PERSISTENT, PathConst.ORG, "/",
				path, "/N");
		commonService.create(CreateMode.PERSISTENT, PathConst.ORG, "/", path,
				"/N/S");
		commonService.create(CreateMode.PERSISTENT, PathConst.ORG, "/", path,
				"/N/M");
		commonService.create(code, CreateMode.PERSISTENT, PathConst.NODE_IDX, "/",
				code);
		log.info("add node to zookeeper======>>{}", node);
	}

	@Override
	public void init(Map<String, Node> cache) {
		List<String> children = commonService.list(PathConst.NODE_IDX);
		for (String child : children) {
			log.info("init node cache ======>>", child);
			Node node = commonService.<Node> getData(PathConst.ORG, "/",
					Commons.getPathByCode(child), "/N");
			cache.put(child, node);
		}
	}

}
