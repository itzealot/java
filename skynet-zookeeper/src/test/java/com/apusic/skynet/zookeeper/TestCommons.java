package com.apusic.skynet.zookeeper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.apusic.skynet.zookeeper.common.Commons;

public class TestCommons {

	@Test
	public void testSubPath() {
		assertEquals("value is not equal.", Commons.subPath(null), "");
		assertEquals("value is not equal.", Commons.subPath(""), "");
		assertEquals("value is not equal.", Commons.subPath("abc"), "");
		assertEquals("value is not equal.", Commons.subPath("abc/cde"), "");

		assertEquals("value is not equal.", Commons.subPath("/"), "");

		assertEquals("value is not equal.", Commons.subPath("/a/b/c"), "/b/c");

		assertEquals("value is not equal.", Commons.subPath(Commons.subPath("/a/b/c")), "/c");
	}

	@Test
	public void testFirstDirName() {
		assertEquals("value is not equal.", Commons.firstDirName("/"), "/");
		assertEquals("value is not equal.", Commons.firstDirName("/a/b/c"), "a");
		assertEquals("value is not equal.", Commons.firstDirName("/cc/a/b/c"), "cc");
		assertEquals("value is not equal.", Commons.firstDirName("/aaa/b/c"), "aaa");
	}

	@Test
	public void testIsMulityPath() {
		assertEquals("value is not equal.", Commons.isMulityPath("/"), false);
		assertEquals("value is not equal.", Commons.isMulityPath("/a"), false);
		assertEquals("value is not equal.", Commons.isMulityPath("/a/"), false);
		assertEquals("value is not equal.", Commons.isMulityPath("/a/b"), true);
		assertEquals("value is not equal.", Commons.isMulityPath("/a//"), true);
	}

	@Test
	public void testFirstPath() {
		assertEquals("value is not equal.", Commons.firstPath("/a/b/c"), "/a");
		assertEquals("value is not equal.", Commons.firstPath("/"), "/");
	}

	@Test
	public void testSpilit() {
		System.out.println(Commons.spilit("/a/b/c", "/"));
	}

	@Test
	public void testDirName() {
		System.out.println(Commons.dirName("/"));
		System.out.println(Commons.dirName("/a"));
		System.out.println(Commons.dirName("/a/b"));
		System.out.println(Commons.dirName("/a/b/c"));
		System.out.println(Commons.dirName("/a/b/c/"));
	}

	@Test
	public void testGetParentPath() {
		System.out.println(Commons.getParentPath("/"));
		System.out.println(Commons.getParentPath("/a"));
		System.out.println(Commons.getParentPath("/a/b"));
		System.out.println(Commons.getParentPath("/a/b/c"));
		System.out.println(Commons.getParentPath("/a/b/c/"));
	}

}
