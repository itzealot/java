package com.projects.sky.zookeeper.test;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class Demo {
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) throws IOException {
		ZooKeeper zkp = new ZooKeeper("localhost:2181", TIMEOUT, null);
		try {
			// 创建一个EPHEMERAL类型的节点，会话关闭后它会自动被删除
			zkp.create("/node1", "data1".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL);

			if (zkp.exists("/node1", false) != null) {
				System.out.println("node1 exists now.");
			}

			try {
				// 当节点名已存在时再去创建它会抛出KeeperException(即使本次的ACL、CreateMode和上次的不一样)
				zkp.create("/node1", "data1".getBytes(), Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);
			} catch (KeeperException e) {
				System.out.println("KeeperException caught:" + e.getMessage());
			}

			// 关闭会话
			zkp.close();

			zkp = new ZooKeeper("localhost:2181", TIMEOUT, null);

			// 重新建立会话后node1已经不存在了
			if (zkp.exists("/node1", false) == null) {
				System.out.println("node1 dosn't exists now.");
			}
			// 创建SEQUENTIAL节点
			zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);
			zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);
			zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);

			List<String> children = zkp.getChildren("/", null);
			System.out.println("Children of root node:");
			for (String child : children) {
				System.out.println(child);
			}

			zkp.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}