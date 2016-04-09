package com.sky.projects.zookeeper;

import junit.framework.Test;
import junit.framework.TestSuite;

import static org.junit.Assert.assertNotEquals;

public class ZkConnectionTest extends BaseJunitTest {

	public ZkConnectionTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testCreate() {
		assertNotEquals("connection is null", null, connection);
	}

}
