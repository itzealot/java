package com.apusic.skynet.zookeeper.listener;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkConnectionStateListener implements ConnectionStateListener {

	private Logger logger = LoggerFactory.getLogger(ZkConnectionStateListener.class);

	// 计数器，用于互斥访问
	private CountDownLatch counter;

	public ZkConnectionStateListener(CountDownLatch counter) {
		this.counter = counter;
	}

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {

		// 状改变为 连接状态
		switch (newState) {
		case CONNECTED:
			logger.info(".................ConnectionState state: {}.................", ConnectionState.CONNECTED);
			try {

				// 保持连接，其他进程阻塞
				counter.await();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			break;

		case LOST:
			logger.info(".................ConnectionState state: {}.................", ConnectionState.LOST);

			try {

			} finally {
				// 连接断开
				counter.countDown();
			}

			break;

		default:
			break;
		}
	}

	public CountDownLatch getCounter() {
		return counter;
	}

}
