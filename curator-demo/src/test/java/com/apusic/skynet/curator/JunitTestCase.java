package com.apusic.skynet.curator;

import org.apache.curator.framework.CuratorFramework;
import org.junit.After;
import org.junit.Before;

public class JunitTestCase {
	protected CuratorFramework client = ClientFactory.newClient();

	// 一路点到底，所谓的 Fluent 风格
	final protected String ROOT = "com.apusic.skynet/zookeeper";

	@Before
	public void before() {
		client.start();
	}

	@After
	public void after() {
		client.close();
	}
}
