package com.sky.projects.redis.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sky.projects.redis.Pipelines;

import redis.clients.jedis.Jedis;

public class TestPipelines {

	Jedis jedis = new Jedis("localhost", 6379);

	@Test
	public void testHgets() {
		System.out.println(Pipelines.hgets(jedis, "myhash", Arrays.asList("name", "sex", "age")));
		System.out.println(Pipelines.hgets(jedis, Arrays.asList("myhash"), Arrays.asList("name")));
	}

	@Test
	public void testSets() {
		System.out.println(Pipelines.sets(jedis, Arrays.asList("name", "test1"), Arrays.asList("name", "test2")));
	}

	@Test
	public void testHexists() {
		System.out.println(Pipelines.hexists(jedis, "myhash", Arrays.asList("name", "sex", "age2", "remark", "age")));
		System.out.println(Pipelines.hexists(jedis, Arrays.asList("myhash"), Arrays.asList("name")));
		System.out.println(Pipelines.hexists(jedis, Arrays.asList("myhash"), Arrays.asList("name2")));
		System.out.println(Pipelines.hexists(jedis, Arrays.asList("myhash"), Arrays.asList("sex")));
	}

	@Test
	public void testGets() {
		System.out.println(Pipelines.gets(jedis, Arrays.asList("name", "mynum", "name")));
	}

	@Test
	public void testHgetAlls() {
		List<Map<String, String>> res = Pipelines.hgetAlls(jedis, Arrays.asList("myhash"));

		System.out.println(res.size());
		System.out.println(res.get(0).get("name"));
		System.out.println(res.get(0).get("sex"));
	}
}
