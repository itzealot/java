package com.projects.sky.zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * watcher分为两大类：data watches和child watches.getData() 和 exists() 上可以设置data
 * watches，getChildren()上可以设置child watches。
 * 
 * setData()会触发data watches;
 * 
 * create()会触发data watches 和 child watches;
 * 
 * delete()会触发data watches 和 child watches.
 * 
 * 如果对一个不存在的节点调用了exists()，并设置了 watcher ，而在连接断开的情况下create/delete了该 znode ，则
 * watcher 会丢失 。
 * 
 * 在server端用一个map来存放watcher，所以相同的watcher在map中只会出现一次，只要watcher被回调一次，它就会被删除----
 * map解释了watcher的一次性。比如如果在getData()和exists()上设置的是同一个 data watcher，调用 setData()
 * 会触发data watcher，但是getData()和exists()只有一个会收到通知。
 * 
 * 
 * @author zt
 * @date 2016年1月6日
 */
public class SelfWatcher implements Watcher {

	ZooKeeper zk = null;

	@Override
	public void process(WatchedEvent event) {
		System.out.println(event.toString());
	}

	SelfWatcher(String address) {
		try {

			// 在创建ZooKeeper时第三个参数负责设置该类的默认构造函数
			zk = new ZooKeeper(address, 3000, this);
			zk.create("/root", new byte[0], Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL);
		} catch (IOException e) {
			e.printStackTrace();
			zk = null;
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void setWatcher() {
		try {
			Stat s = zk.exists("/root", true);
			if (s != null) {
				zk.getData("/root", false, s);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void trigeWatcher() {
		try {
			// 此处不设置watcher
			Stat s = zk.exists("/root", false);

			// 修改数据时需要提供version，version设为-1表示强制修改
			zk.setData("/root", "a".getBytes(), s.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (zk != null)
			try {
				zk.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		SelfWatcher inst = new SelfWatcher("127.0.0.1:2181");
		inst.setWatcher();
		inst.trigeWatcher();
		inst.disconnect();
	}

}