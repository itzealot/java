package com.projects.sky.zookeeper;

import org.junit.Test;

import com.projects.sky.zookeeper.common.Commons;

public class TestCommons {

	@Test
	public void testSpilit() {
		System.out.println(Commons.spilit("/a/b/c", "/"));
	}

	@Test
	public void checkPath() {
		Commons.checkPath("/");
		Commons.checkPath("/a");
		Commons.checkPath("/a/b");
	}

}
