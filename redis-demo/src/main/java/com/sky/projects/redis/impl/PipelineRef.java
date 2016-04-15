package com.sky.projects.redis.impl;

import java.lang.reflect.Method;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public final class PipelineRef {

	private PipelineRef() {
	}

	/**
	 * invoke for methods like : <br />
	 * --1). get(String key)
	 * 
	 * --2). hgetAll(String key)
	 * 
	 * @param pipeline
	 * @param key
	 * @param e
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Response<T> invoke(Pipeline pipeline, String key, String method) {
		Method m = null;
		Response<T> response = null;

		try {
			m = Pipeline.class.getMethod(method, String.class);

			response = (Response<T>) m.invoke(pipeline, key);

		} catch (Exception e) {
			throw new IllegalArgumentException("don't have the method like name is: " + method);
		}

		return response;
	}

	/**
	 * invoke for methods like : <br />
	 * --1). set(String key, String value)
	 * 
	 * --2). hexists(String key, String field)
	 * 
	 * --3). hget(String key, String field)
	 * 
	 * --4). expire(String key, int seconds)
	 * 
	 * 
	 * @param pipeline
	 * @param key
	 * @param e
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> Response<T> invoke(Pipeline pipeline, String key, E e, String method) {
		Method m = null;
		Response<T> response = null;

		try {
			m = Pipeline.class.getMethod(method, String.class, e.getClass());

			response = (Response<T>) m.invoke(pipeline, key, e);

		} catch (Exception e1) {
			throw new IllegalArgumentException("don't have the method like name is: " + method);
		}

		return response;
	}

	/**
	 * invoke for methods like : <br />
	 * --1). hset(String key, String field, String value)
	 * 
	 * --2). hincrBy(String key, String field, long value)
	 * 
	 * @param pipeline
	 * @param key
	 * @param field
	 * @param value
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> Response<T> invoke(Pipeline pipeline, String key, String field, E value, String method) {
		Method m = null;
		Response<T> response = null;

		try {
			m = Pipeline.class.getMethod(method, String.class, String.class, value.getClass());

			response = (Response<T>) m.invoke(pipeline, key, field, value);

		} catch (Exception e1) {
			throw new IllegalArgumentException("don't have the method like name is: " + method);
		}

		return response;
	}
}
