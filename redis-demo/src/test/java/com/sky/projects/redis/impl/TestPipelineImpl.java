package com.sky.projects.redis.impl;

import java.util.Arrays;

import com.sky.projects.redis.demo.PipelineImpl;

import redis.clients.jedis.Jedis;

public class TestPipelineImpl {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);

		PipelineImpl impl = new PipelineImpl(jedis);

		System.out.println(impl.execute(Arrays.asList("myhash"), Arrays.asList("sex")));
	}
}
