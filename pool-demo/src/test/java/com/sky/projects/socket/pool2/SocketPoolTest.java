package com.sky.projects.socket.pool2;

import java.net.Socket;
import java.util.Random;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.sky.projects.scoket.pool2.SocketPool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SocketPoolTest extends TestCase {

	public SocketPoolTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(SocketPoolTest.class);
	}

	public void testApp() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();

		// 最大连接数, 默认8个
		config.setMaxIdle(30);

		// 在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(true);

		// 最小空闲连接数, 默认0
		config.setMinIdle(20);
		config.setMaxTotal(100);

		config.setMaxWaitMillis(1000 * 1000);

		SocketPool pool = SocketPool.getInstance(config, "127.0.0.1", 49152);
		for (int i = 0; i < 10; i++) {
			new Thread(new SocketRunner(pool)).start();
		}
		
//		for(int i = 0; i < 40; i++) {
//			System.out.println(pool.getSocket());
//		}
	}

	static class SocketRunner implements Runnable {
		private SocketPool pool;

		public SocketRunner(SocketPool pool) {
			this.pool = pool;
		}

		@Override
		public void run() {
			Socket socket = null;

			try {
				socket = pool.getSocket();

				int sleep = 100 + new Random().nextInt(200);
				System.out.println(Thread.currentThread().getName() + " : " + socket + " sleep " + sleep);

				Thread.sleep(sleep);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.returnSocket(socket);
			}
		}

	}
}
