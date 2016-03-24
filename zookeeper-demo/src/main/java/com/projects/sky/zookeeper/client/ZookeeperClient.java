package com.projects.sky.zookeeper.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zookeeper Client
 * 
 * @author zt
 * @date 2016年1月6日
 */
public class ZookeeperClient {

	private final static Logger log = LoggerFactory
			.getLogger(ZookeeperClient.class);

	private static String zkServer = "192.168.2.88";

	private static Integer sessionTime;

	private static ZooKeeper zooKeeper;

	private Lock lock;

	private Condition condition;

	private static CountDownLatch countDownLatch;

	private ExecutorService executorService = Executors.newCachedThreadPool();

	private ZookeeperClient() {
		super();
		lock = new ReentrantLock();
		condition = lock.newCondition();
		countDownLatch = new CountDownLatch(1);
	}

	public static synchronized ZooKeeper getZookeeper() {
		try {
			if (zooKeeper == null) {
				ZookeeperClient client = new ZookeeperClient();
				client.connection();
			}
			countDownLatch.await();
		} catch (InterruptedException e) {
			log.info(e.getMessage());
		}
		return zooKeeper;
	}

	private Watcher getWatcher() {
		return new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (event.getType() == Event.EventType.None) {
					switch (event.getState()) {
					case SyncConnected:
						log.info("===Zookeeper connected ===\n");
						countDownLatch.countDown();
						break;
					case Disconnected:
						log.info("===Zookeeper connection Disconnected ===\n");
					case Expired:
						lock.lock();
						log.info("===Zookeeper connection Timeout ===\n");
						condition.signal();
						lock.unlock();
						break;
					default:
						break;
					}
				}
			}
		};
	}

	private void connection() {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					while (true) {
						zooKeeper = new ZooKeeper(zkServer, sessionTime,
								getWatcher());
						condition.await();
					}
				} catch (IOException e) {
					log.debug(e.getMessage());
					condition.signal();
					countDownLatch = new CountDownLatch(1);
				} catch (InterruptedException e) {
					log.debug(e.getMessage());
				} finally {
					lock.unlock();
				}
			}
		});
	}
}
