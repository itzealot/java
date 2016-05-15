package com.surfilter.mass.thread;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.surfilter.mass.redis.pool.RedisPool;

import redis.clients.jedis.Jedis;

public class RedisMacProducter implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(RedisMacProducter.class);

	private BlockingQueue<String> queue;
	private String pattern;
	private RedisPool pool;
	private Jedis jedis;

	public RedisMacProducter(BlockingQueue<String> queue, String pattern, Jedis jedis, RedisPool pool) {
		this.queue = queue;
		this.pattern = pattern;
		this.pool = pool;
		this.jedis = jedis;
	}

	@Override
	public void run() {
		Set<String> keys = jedis.keys(pattern);

		for (String key : keys) {
			try {
				queue.put(key);
			} catch (InterruptedException e) {
				LOG.error("put error, key is : {}, errors is : {}", key, e);
			}
		}

		LOG.debug("receive keys from jedis size: {}, where the pattern is : {}", keys.size(), pattern);

		pool.returnResource(jedis);
	}

}
