package com.apusic.skynet.zookeeper;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ZkConnectionTest extends BaseJunitTest {

	@Test
	public void testCreate() {
		assertNotEquals("connection is null", null, connection);
	}

}
