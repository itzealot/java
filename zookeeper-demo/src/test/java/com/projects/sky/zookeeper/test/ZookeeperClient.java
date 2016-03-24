package com.projects.sky.zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperClient {
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) throws IOException, KeeperException,
			InterruptedException {

		/**
		 * ZooKeeper zkp = new ZooKeeper(
		 * "192.168.119.96:2181, 192.168.119.97:2181, 192.168.119.98:2181/app/learn"
		 * , TIMEOUT, null));
		 * 
		 * client 连接 zookeeper 连接的时候，最好指定>=2个zookeeper服务器地址, 这样当一个zookeeper当掉后，
		 * client会自动failover到其它的连接。这时候default watcher 会先收到一个 Disconnected
		 * 事件，连接的新的 Zookeeper 服务器后会收到一个 SyncConnected 事件 。这种情况下 session
		 * 建立的临时节点不会丢失，所以程序一般不需要对这点做什么特别的工作。
		 * 
		 * --1).如果你指定的zookeeper中有leader ,它好像会直接连接到 leader，如果是 global
		 * 集群或多机房集群，建议只指定本机房的zooleeper。
		 * 
		 * --2).如果client连接指定的所有 zookeeper server 都当掉了，先还是会收到一个 Disconnected
		 * 事件，然后临时节点消失，启动zookeeper server后会收到Expired事件。
		 * 
		 */

		/**
		 * Client向zookeeper发送连接请求
		 * 
		 * ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
		 * 
		 * @Param connectString : 连接地址串，zookeeper 集群使用 "," 分隔；IP:Port
		 *        列表(当client连接不上server时会按照此列表尝试连接下一台server)，以及默认的根目录
		 * 
		 * @Param sessionTimeout : Session Timeout
		 * 
		 * @Param watcher : 是否设置监听器
		 * 
		 */
		ZooKeeper zkp = new ZooKeeper("192.168.119.96:2181/app/learn", TIMEOUT,
				null);

		/**
		 * 创建数据节点
		 * 
		 * create(String path, byte[] data, List<ACL> acl, CreateMode
		 * createMode)
		 * 
		 * @Param path : 节点名称
		 * 
		 * @Param data : 节点上的数据
		 * 
		 * @Param acl : ACL
		 * 
		 * @Param createMode :
		 *        三种节点类型：PERSISTENT(持久的)、EPHEMERAL(短暂的)、SEQUENTIAL;EPHEMERAL节点不允许有子节点
		 */
		zkp.create("/znodename", "znodedata".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL);
		/**
		 * 判断节点是否存在
		 * 
		 * exists(String path, boolean watch)
		 * 
		 * @Param path : 节点路径，如果节点不存在，则返回null
		 * 
		 * @Param watch : 是否设置监听器
		 */
		Stat stat = zkp.exists("/znodename", false);
		if (zkp.exists("/znodename", false) != null) {
			System.out.println("znodename exists now.");
		}

		/**
		 * 修改节点上存储的数据，需要提供version，version设为-1表示强制修改
		 * 
		 * setData(String path, byte[] data, int version)
		 * 
		 * @Param path : 节点路径
		 * 
		 * @Param data : 数据
		 * 
		 * @Param version : 版本，为-1表示强制修改
		 * 
		 */
		zkp.setData("/znodename", "newdata".getBytes(), stat.getVersion());

		/**
		 * 读取节点上的数据
		 * 
		 * getData(String path, boolean watch, Stat stat)
		 * 
		 * @Param path : 节点路径
		 * 
		 * @Param watch : 是否设置监听器
		 * 
		 * @Param stat : 数据
		 */
		String data = new String(zkp.getData("/znodename", false, stat));
		System.out.println(data);

		/**
		 * client端主动断开连接
		 * 
		 * close()
		 */
		zkp.close();
	}
}