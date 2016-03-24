package com.projects.sky.zookeeper.monitor.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.client.ZookeeperClient;
import com.projects.sky.zookeeper.common.Commons;
import com.projects.sky.zookeeper.listener.MonitorCallBack;
import com.projects.sky.zookeeper.monitor.CommonMonitor;

public class CommonMonitorImpl implements CommonMonitor {

	private final Logger log = LoggerFactory.getLogger(CommonMonitor.class);
	private ExecutorService pool;
	private ZooKeeper zooKeeper;

	public CommonMonitorImpl(ExecutorService pool) {
		zooKeeper = ZookeeperClient.getZookeeper();
		this.pool = pool;
	}

	@Override
	public void createChilrendMoniotr(MonitorCallBack callBack,
			final String... path) {
		String node = Commons.joinPath(path);
		if (!exists(node)) {
			log.info("Path is not exists");
			return;
		}
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();
		pool.submit(new Runnable() {
			@Override
			public void run() {
				log.info("=========create children monitor {}=========", node);
				lock.lock();
				try {
					while (true) {
						zooKeeper.getChildren(node, new Watcher() {

							@Override
							public void process(WatchedEvent watchedEvent) {
								lock.lock();
								condition.signal();
								lock.unlock();
							}
						}, new AsyncCallback.ChildrenCallback() {
							@Override
							public void processResult(int code, String path,
									Object ctx, List<String> list) {
								callBack.monitorChildren(path, list);
							}
						}, null);
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
	}

	@Override
	public void createDataMonitor(MonitorCallBack callBack, String... path) {
		String node = Commons.joinPath(path);
		if (!exists(node)) {
			log.info("Path is not exists");
			return;
		}
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();
		pool.submit(new Runnable() {
			@Override
			public void run() {
				log.info("=========create data monitor {}=========", node);
				lock.lock();
				try {
					while (true) {
						zooKeeper.getData(node, new Watcher() {

							@Override
							public void process(WatchedEvent watchedEvent) {
								lock.lock();
								condition.signal();
								lock.unlock();
							}
						}, new AsyncCallback.DataCallback() {

							@Override
							public void processResult(int code, String path,
									Object obj, byte[] bytes, Stat stat) {

								callBack.monitorData(path, bytes);
							}
						}, null);
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
	}

	@Override
	public void createExistsMonitor(MonitorCallBack callBack, String... path) {
		String node = Commons.joinPath(path);
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();
		pool.submit(new Runnable() {
			@Override
			public void run() {
				log.info("=========create exists monitor {}=========", node);
				lock.lock();
				try {
					while (true) {
						zooKeeper.exists(node, new Watcher() {

							@Override
							public void process(WatchedEvent watchedEvent) {
								lock.lock();
								condition.signal();
								lock.unlock();
							}
						}, new AsyncCallback.StatCallback() {

							@Override
							public void processResult(int code, String path,
									Object obj, Stat stat) {
								callBack.monitorExists(path);
							}
						}, null);
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
	}

	private boolean exists(String path) {
		try {
			if (zooKeeper.exists(path, false) == null)
				return false;
			else
				return true;
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}
}
