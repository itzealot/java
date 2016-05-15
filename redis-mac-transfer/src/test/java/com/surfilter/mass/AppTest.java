package com.surfilter.mass;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;

public class AppTest extends TestCase {
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		Jedis jedis = new Jedis("localhost", 5666);
		System.out.println(jedis.ping());
		jedis.close();
		assertTrue(true);
	}

	public void test() {
		char[] prefixs = "0123456789abcdeABCDE".toCharArray();
		for (char ch : prefixs) {
			// 获取 mac 所有的 keys
			String pattern = "m_" + ch + "*";
			System.out.println(pattern);
		}
	}
}
