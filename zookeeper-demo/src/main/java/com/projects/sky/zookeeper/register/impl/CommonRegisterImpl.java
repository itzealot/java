package com.projects.sky.zookeeper.register.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.client.ZookeeperClient;
import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.register.CommonRegister;

public class CommonRegisterImpl implements CommonRegister {

	private final Logger log = LoggerFactory.getLogger(CommonRegister.class);

	private ZooKeeper zooKeeper;

	public CommonRegisterImpl() {
		zooKeeper = ZookeeperClient.getZookeeper();
	}

	@Override
	public <T> String create(T t, CreateMode mode, String... path) {
		String znode = null;
		try {
			znode = zooKeeper.create(Commons.joinPath(path),
					Commons.serialize(t), ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
			log.info("Create node {}\n", znode);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
		return znode;
	}

	@Override
	public String create(CreateMode mode, String... path) {
		String znode = null;
		try {
			znode = zooKeeper.create(Commons.joinPath(path), null,
					ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
			log.info("Create node {}\n", znode);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
		return znode;
	}

	@Override
	public boolean delete(String... path) {
		try {
			if (zooKeeper.exists(Commons.joinPath(path), false) == null) {
				log.info("Node {} not exist \n", Commons.joinPath(path));
			}
			if (zooKeeper.getChildren(Commons.joinPath(path), false).isEmpty()) {
				zooKeeper.delete(Commons.joinPath(path), -1);
				log.info("Node {} was deleted\n", Commons.joinPath(path));
				return true;
			} else {
				log.info(
						"Node {} is can't delete because it have children node \n",
						Commons.joinPath(path));
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
		return false;
	}

	@Override
	public <T> T getData(String... path) {
		try {
			byte[] data = zooKeeper
					.getData(Commons.joinPath(path), false, null);
			return Commons.<T> deserialize(data);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
		return null;
	}

	@Override
	public <T> void setData(T t, String... path) {
		try {
			if (exists(path)) {
				zooKeeper.setData(Commons.joinPath(path), Commons.serialize(t),
						-1);
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
	}

	@Override
	public List<String> list(String... path) {
		List<String> children = new ArrayList<String>();
		try {
			children = zooKeeper.getChildren(Commons.joinPath(path), false);
			if (children.isEmpty()) {
				log.info("No child node \n");
				return children;
			}
			return children;
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
		return children;
	}

	@Override
	public boolean exists(String... path) {
		try {
			if (zooKeeper.exists(Commons.joinPath(path), false) == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			// TODO
		}
		return false;
	}

}
