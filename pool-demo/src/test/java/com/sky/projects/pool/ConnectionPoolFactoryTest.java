package com.sky.projects.pool;

import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConnectionPoolFactoryTest extends TestCase {
	public ConnectionPoolFactoryTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ConnectionPoolFactoryTest.class);
	}

	public void testApp() {
		Config config = new Config();

		config.maxActive = 16;
		config.maxWait = 30000;

		ConnectionPoolFactory poolFactory = new ConnectionPoolFactory(config, "127.0.0.1", 49152);
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
