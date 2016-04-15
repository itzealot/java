package com.sky.projects.redis;

import java.util.List;

import com.sky.projects.redis.impl.PipelineResult;

import redis.clients.jedis.Jedis;

public final class Pipelines {
	private Pipelines() {
	}

	public static <T> List<T> gets(Jedis jedis, final List<String> keys) {
		return PipelineResult.resultsOf(jedis, keys, "get");
	}

	public static <T, E> List<T> sets(Jedis jedis, final List<String> keys, List<String> values) {
		return PipelineResult.resultsOf(jedis, keys, values, "set");
	}

	public static <T> List<T> hgetAlls(Jedis jedis, final List<String> keys) {
		return PipelineResult.resultsOf(jedis, keys, "hgetAll");
	}

	public static <T> List<T> hgets(Jedis jedis, String key, final List<String> fileds) {
		return PipelineResult.resultsOf(jedis, key, fileds, "hget");
	}

	public static <T> List<T> hgets(Jedis jedis, final List<String> keys, final List<String> fileds) {
		return PipelineResult.resultsOf(jedis, keys, fileds, "hget");
	}

	public static <T> List<T> hexists(Jedis jedis, String key, final List<String> fileds) {
		return PipelineResult.resultsOf(jedis, key, fileds, "hexists");
	}

	public static <T> List<T> hexists(Jedis jedis, List<String> keys, final List<String> fileds) {
		return PipelineResult.resultsOf(jedis, keys, fileds, "hexists");
	}
}
