package com.sky.projects.redis.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public final class PipelineResult {
	private PipelineResult() {
	}

	private static <T> List<T> resultsOf(List<Response<T>> responses) {
		List<T> results = new ArrayList<T>();

		for (Response<T> response : responses) {
			results.add(response.get());
		}

		return results;
	}

	/**
	 * batch for methods like : get,
	 * 
	 * @param jedis
	 * @param keys
	 * @param method
	 * @return
	 */
	public static <T> List<T> resultsOf(Jedis jedis, final List<String> keys, String method) {
		check(jedis, keys, method);

		List<Response<T>> responses = new ArrayList<>();

		Pipeline pipeline = jedis.pipelined();

		for (String key : keys) {
			Response<T> response = PipelineRef.invoke(pipeline, key, method);

			responses.add(response);
		}

		pipeline.sync();

		return resultsOf(responses);
	}

	/**
	 * batch for methods like : hget, hexists
	 * 
	 * @param jedis
	 * @param key
	 * @param fileds
	 * @param method
	 * @return
	 */
	public static <T> List<T> resultsOf(Jedis jedis, final String key, final List<String> fileds, String method) {
		check(jedis, key, fileds, method);

		List<Response<T>> responses = new ArrayList<>();

		Pipeline pipeline = jedis.pipelined();

		for (String filed : fileds) {
			Response<T> response = PipelineRef.invoke(pipeline, key, filed, method);

			responses.add(response);
		}

		pipeline.sync();

		return resultsOf(responses);
	}

	/**
	 * batch for methods like : hget, hexists, set
	 * 
	 * @param jedis
	 * @param keys
	 * @param fileds
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> resultsOf(Jedis jedis, final List<String> keys, final List<String> fileds,
			String method) {

		check(jedis, method);
		check(keys, fileds);

		List<Response<T>> responses = new ArrayList<>();

		Pipeline pipeline = jedis.pipelined();

		Method m = null;

		try {
			m = Pipeline.class.getMethod(method, String.class, String.class);

			int len = keys.size();
			for (int i = 0; i < len; i++) {
				Response<T> response = (Response<T>) m.invoke(pipeline, keys.get(i), fileds.get(i));
				responses.add(response);
			}

			pipeline.sync();

		} catch (Exception e) {
			throw new IllegalArgumentException("don't have the method like name is: " + method);
		}

		return resultsOf(responses);
	}

	/**
	 * batch for methods like : set
	 * 
	 * @param jedis
	 * @param keys
	 * @param fileds
	 * @param values
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> List<T> resultsOf(Jedis jedis, final List<String> keys, final List<String> fileds,
			List<E> values, String method) {
		check(jedis, method);
		check(keys, fileds);

		List<Response<T>> responses = new ArrayList<>();

		Pipeline pipeline = jedis.pipelined();

		Method m = null;

		try {
			m = Pipeline.class.getMethod(method, String.class);

			for (String key : keys) {
				Response<T> response = (Response<T>) m.invoke(pipeline, key);

				responses.add(response);
			}

			pipeline.sync();

		} catch (Exception e) {
			throw new IllegalArgumentException("don't have the method like name is: " + method);
		}

		return resultsOf(responses);
	}

	private static void check(List<String> l1, List<String> l2) {
		checkNotNull(l1, "keys must not be null");
		checkNotNull(l2, "fileds must not be null");

		if (l1.size() != l2.size()) {
			throw new IllegalArgumentException("keys size doesn't equal to fileds's size");
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> void check(T... objs) {
		checkNotNull(objs, "objs must not be null");

		int len = objs.length;

		if (len == 0) {
			throw new IllegalArgumentException("objs size must not be null");
		}

		for (int i = 0; i < len; i++) {
			checkNotNull(objs[i], "the reference must not be null, the index is : " + i);
		}
	}
}
