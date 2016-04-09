package com.sky.projects.zookeeper;

import com.sky.projects.zookeeper.common.Commons;

public class TestCommons {

	public void testSpilit() {
		System.out.println(Commons.spilit("/a/b/c", "/"));
	}

	public void checkPath() {
		Commons.checkPath("/");
		Commons.checkPath("/a");
		Commons.checkPath("/a/b");
	}

}
