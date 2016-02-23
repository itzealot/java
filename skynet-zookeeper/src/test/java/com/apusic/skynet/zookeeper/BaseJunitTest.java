package com.apusic.skynet.zookeeper;

import org.junit.After;

public class BaseJunitTest {
	private String connectionString = "localhost:2181";
	protected String namespace = "com.apusic.test";
	protected ZkConnection connection = new ZkConnectionFactory(connectionString, namespace).createConnection();

	@After
	public void close() throws Exception {
		connection.close();
	}
}
