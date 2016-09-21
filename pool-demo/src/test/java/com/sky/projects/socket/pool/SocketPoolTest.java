package com.sky.projects.socket.pool;

import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import com.sky.projects.scoket.pool.SocketPool;

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
		Config config = new Config();

		config.maxActive = 16;
		config.maxWait = 30000;

		SocketPool poolFactory = new SocketPool(config, "127.0.0.1", 49152);
		Socket socket = null;

		try {
			socket = poolFactory.get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				poolFactory.release(socket);
			}
		}
	}
}
