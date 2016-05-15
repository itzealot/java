package com.sky.projects.redis;

import java.util.List;

import com.sky.projects.redis.Redis.PipelineNoResultAction;
import com.sky.projects.redis.Redis.PipelineResultAction;
import com.sky.projects.redis.Redis.RedisNoResultAction;
import com.sky.projects.redis.Redis.RedisResultAction;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisTest extends TestCase {

	private String host = "localhost";
	private int port = 6379;
	private Jedis jedis = new Jedis(host, port);
	private Redis redis = new Redis(jedis);

	public RedisTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(RedisTest.class);
	}

	public void testRedisResultAction() {
		final String key = "key1";
		final String value = "value1";

		redis.execute(new RedisNoResultAction() {
			public void action(Jedis jedis) {
				jedis.set(key, value);
			}
		});

		String value2 = redis.execute(new RedisResultAction<String>() {
			public String action(Jedis jedis) {
				return jedis.get(key);
			}
		});

		assertEquals("value is not equal", value, value2);
	}

	public void testPipelineNoResultAction() {
		final String key2 = "key2";
		final String value2 = "value2";

		final String key3 = "key3";
		final String value3 = "value3";

		redis.execute(new PipelineNoResultAction() {
			public void action(Pipeline pipeline) {
				pipeline.set(key2, value2);
				pipeline.set(key3, value3);
			}
		});

		List<String> results = redis.execute(new PipelineResultAction<String>() {
			public void action(Pipeline pipeline) {
				pipeline.get(key2);
				pipeline.get(key3);
			}
		});

		assertEquals("value is not equal", results.get(0), value2);
		assertEquals("value is not equal", results.get(1), value3);
	}
}
