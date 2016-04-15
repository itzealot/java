package com.sky.projects.redis;

import com.sky.projects.redis.demo.Redis;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RedisTest extends TestCase {
	private Redis redis = Redis.getInstance(Redis.DEFAULT_HOST, Redis.DEFAULT_PORT);

	public RedisTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(RedisTest.class);
	}

	public void testPing() {
		System.out.println("Server is running: " + redis.ping());
	}

	public void testGet() {
		System.out.println("mynum value is : " + redis.get("mynum"));
	}

	public void testKeys() {
		System.out.println("Server is running: " + redis.keys());
	}

	public void testLists() {
		redis.lpush("tutorial-list", "Redis");
		redis.lpush("tutorial-list", "Mongodb");
		redis.lpush("tutorial-list", "Mysql");

		System.out.println(redis.lrange("tutorial-list", 0, 5));
	}

	public void testString() {
		redis.set("tutorial-name", "Redis tutorial");

		System.out.println("Stored string in redis: " + redis.get("tutorial-name"));
	}

}
