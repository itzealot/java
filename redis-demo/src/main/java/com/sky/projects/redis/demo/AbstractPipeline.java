package com.sky.projects.redis.demo;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public abstract class AbstractPipeline {

	protected Pipeline pipeline;
	protected Jedis jedis;

	public AbstractPipeline(Jedis jedis) {
		checkNotNull(jedis, "Jedis must not be null");

		this.jedis = jedis;
	}

	@SuppressWarnings("unchecked")
	public abstract <T> List<Response<T>> beforeBatch(Pipeline pipeline, List<String>... lists);

	public abstract <T> List<T> afterBatch(List<Response<T>> responses);

	@SafeVarargs
	public final <T> List<T> execute(List<String>... lists) {
		List<Response<T>> responses = beforeBatch(pipeline, lists);

		pipeline.sync();

		return afterBatch(responses);
	}

}
