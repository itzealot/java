package com.sky.projects.zookeeper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BaseJunitTest extends TestCase {
	private String connectionString = "localhost:2181";
	protected String namespace = "com.apusic.test";
	protected ZkConnection connection = new ZkConnectionFactory(connectionString, namespace).createConnection();

	public BaseJunitTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void close() throws Exception {
		connection.close();
	}
}
