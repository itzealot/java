package com.projects.sky.zookeeper.xml;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import com.projects.sky.zookeeper.common.Commons;

public class Zookeepers {

	private ZooKeeper zooKeeper;

	public Zookeepers(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

	/**
	 * To create node
	 * 
	 * @param t
	 * @param mode
	 * @param path
	 * @return
	 */
	public <T> String create(T t, CreateMode mode, String... path) {
		String znode = null;
		try {
			znode = zooKeeper.create(Commons.joinPath(path), Commons.serialize(t), ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
			System.out.printf("Create node %s\n", znode);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return znode;
	}

	public String create(CreateMode mode, String... path) {
		String znode = null;
		try {
			znode = zooKeeper.create(Commons.joinPath(path), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
			System.out.printf("Create node %s\n", znode);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return znode;
	}

	/**
	 * To delete the node by path
	 * 
	 * @param path
	 * @return
	 */
	public boolean delete(String... path) {
		try {
			if (zooKeeper.exists(Commons.joinPath(path), false) == null) {
				System.out.printf("Node %s not exist \n", Commons.joinPath(path));
			}
			if (zooKeeper.getChildren(Commons.joinPath(path), false).isEmpty()) {
				zooKeeper.delete(Commons.joinPath(path), -1);
				System.out.printf("Node %s was deleted\n", Commons.joinPath(path));
				return true;
			} else {
				System.out.printf("Node %s is can't delete because it have children node \n", Commons.joinPath(path));
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * To get data by path
	 * 
	 * @param path
	 * @return
	 */
	public <T> T getData(String... path) {
		try {
			byte[] data = zooKeeper.getData(Commons.joinPath(path), false, null);
			return Commons.deserialize(data);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To set data by path
	 * 
	 * @param t
	 * @param path
	 */
	public <T> void setData(T t, String... path) {
		try {
			if (exists(path)) {
				zooKeeper.setData(Commons.joinPath(path), Commons.serialize(t), -1);
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
	}

	/**
	 * To get the node's children path
	 * 
	 * @param path
	 * @return
	 */
	public List<String> list(String... path) {
		List<String> children = null;
		try {
			children = zooKeeper.getChildren(Commons.joinPath(path), false);
			if (children.isEmpty()) {
				System.out.printf("No child node \n");
			}
			return children;
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return children;
	}

	/**
	 * To judge the node is existing by path
	 * 
	 * @param path
	 * @return
	 */
	public boolean exists(String... path) {
		try {
			if (zooKeeper.exists(Commons.joinPath(path), false) == null)
				return false;
			return true;
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return false;
	}
}
