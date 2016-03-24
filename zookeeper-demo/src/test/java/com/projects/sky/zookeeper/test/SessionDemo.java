package com.projects.sky.zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.ConnectionLossException;
import org.apache.zookeeper.KeeperException.SessionExpiredException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Session
 * 
 * --1). Zookeeper 为每个client分配一个Session，Session数据在每台zookeeper节点上都有备份。
 * 
 * --2). Server每隔tickTime向Client发送一个心跳。
 * 
 * --3). session失效有两种可能性：client主动调用close() ；Server和Client 长时间(时间大于TIMEOUT)失去联系。
 * 
 * --4). Session失效后与Session关联的数据(比如EPHEMERAL节点，Watcher)会从内存中移除。
 * 
 * --5). 如果Server和Client只是短暂(时间小于TIMEOUT )地失去联系，则 Session 不会失效，Client 再次请求
 * Zookeeper 时会把上次的SessionID带上。
 * 
 * --6). Server 和 Client 失去联系有两种情况：Server 宕机(Zookeeper 集群会让 Client 与另一台 Server
 * 建立连接)；网络异常。
 * 
 * 
 * @author zt
 * @date 2016年1月6日
 */
public class SessionDemo {
	/**
	 * zoo.cfg中的配置：
	 * 
	 * <pre>
	 * tickTime=2000
	 * minSessionTimeout=4000（至少是tickTime的2倍）
	 * maxSessionTimeout=40000（最大是tickTime的20倍）
	 * </pre>
	 * 
	 * 如果客户端建立连接时指定的TIMEOUT不在[minSessionTimeout,maxSessionTimeout]区间内，
	 * 服务端会强制把它修改到该区间内
	 */

	// Timeout设为40秒，因为心跳周期为2秒，所以如果server向client连续发送20个心跳都收不到回应，则Session过期失效
	private static final int TIMEOUT = 40000;

	private static ZooKeeper zkp = null;

	private static String connectString = "192.168.119.96:2181/app/learn";

	/**
	 * To connect Zookeeper
	 * 
	 * @throws IOException
	 */
	private static void connect() throws IOException {
		zkp = new ZooKeeper(connectString, TIMEOUT, null);
	}

	private static void createNode() throws KeeperException,
			InterruptedException {
		if (zkp != null) {
			zkp.create("/znodename", "znodedata".getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		}
	}

	private static String getData() throws KeeperException,
			InterruptedException {
		if (zkp != null) {
			Stat stat = zkp.exists("/znodename", false);
			return new String(zkp.getData("/znodename", false, stat));
		}
		return null;
	}

	private static void disconnect() throws InterruptedException {
		if (zkp != null) {
			zkp.close();
		}
	}

	/**
	 * 休息，在此期间我们有三种选择：<br>
	 * <ol>
	 * <li>永久性断开网络连接
	 * <li>断开网络连接一段时间timespan后再连上，其中timespan<{@code TIMEOUT}
	 * <li>断开网络连接一段时间timespan后再连上，其中timespan>{@code TIMEOUT}
	 * </ol>
	 */
	private static void sleepForNetworkDisturbances() {
		try {
			Thread.sleep(2 * TIMEOUT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			connect();
		} catch (IOException e) {
			System.err.println("Can't create zookeeper client.");
		}
		System.out.println("Session build.");

		try {
			createNode();
		} catch (Exception e) {
			System.err.println("Create znode failed.");
		}
		System.out.println("znode created.");

		sleepForNetworkDisturbances();

		try {
			String data = getData();
			if (data != null) {
				// 在"休息"期间做了第2件事情，Sesion 没有过期，EPHEMERAL节点依然存在
				System.out.println("data=" + data);
			}
		} catch (KeeperException e) {
			// TODO

			if (e instanceof ConnectionLossException) { // 在"休息"期间做了第1件事情
				System.err.println("Network is disconnected. Retry getData().");

				// 如果session没有失效，而仅仅是网络异常，则可以重新尝试获取数据，可能在重试时网络已经正常了
				try {
					Thread.sleep(1000);
					String data = getData();
					if (data != null) {
						System.out.println("data=" + data);
					} else {
						System.out.println("can't get data.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e instanceof SessionExpiredException) {// 在"休息"期间做了第3件事情，则session会过期
				System.err
						.println("Session Expired, client will reconnect and create znode again.");
				// 当发再Session Expired时，必须重新建立连接，即new一个ZooKeeper
				try {
					connect();
					createNode();
					String data = getData();
					if (data != null) {
						System.out.println("data=" + data);
					} else {
						System.out.println("can't get data.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			disconnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Client disconnected.");
	}
}