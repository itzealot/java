package com.sky.projects.redis.demo;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class PipelineImpl extends AbstractPipeline {

	public PipelineImpl(Jedis jedis) {
		super(jedis);
	}

	@SuppressWarnings("unchecked")
	public <T> List<Response<T>> beforeBatch(Pipeline pipeline, List<String>... lists) {
		List<Response<T>> strings = new ArrayList<>();

		pipeline = jedis.pipelined();

		int len = lists[0].size();

		for (int i = 0; i < len; i++) {
			Response<String> string = pipeline.hget(lists[0].get(i), lists[1].get(i));

			strings.add((Response<T>) string);
		}

		return strings;
	}

	@Override
	public <T> List<T> afterBatch(List<Response<T>> responses) {
		List<T> elements = new ArrayList<>();

		for (Response<T> response : responses) {
			elements.add(response.get());
		}

		return elements;
	}

}
