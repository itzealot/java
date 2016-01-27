package com.apusic.skynet.curator;

import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorTempFramework;
import org.junit.Test;

public class TestCuratorTempFramework {
	private CuratorTempFramework client = CuratorFrameworkFactory.builder().buildTemp();

	@Test
	public void testClose() {
		client.close();
	}
}
