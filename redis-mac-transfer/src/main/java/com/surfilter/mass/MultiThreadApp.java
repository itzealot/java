package com.surfilter.mass;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.surfilter.mass.config.RedisMacConf;
import com.surfilter.mass.redis.pool.RedisPool;
import com.surfilter.mass.thread.RedisMacConsumer;

import redis.clients.jedis.Jedis;

/**
 * 多线程并发执行 Redis mac key 程序入口
 * 
 * @author zt
 *
 */
public class MultiThreadApp {

	private static final Logger LOG = LoggerFactory.getLogger(MultiThreadApp.class);

	public static void main(String[] args) {
		RedisMacConf conf = RedisMacConf.getInstance();

		int poolSize = conf.getPoolSize();

		RedisPool pool = new RedisPool(conf.getHost(), conf.getPort(), 600 * 1000);

		Jedis jedis = pool.getResource();
		LOG.debug("host: {}, port: {}, Jedis: {}", conf.getHost(), conf.getPort(), jedis.ping());

		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

		// 根据线程池大小创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);

		// 获取 mac 所有的 keys
		String pattern = conf.getDealMacPartern();
		Set<String> keys = jedis.keys(pattern);

		if (keys.size() == 0) {
			return;
		}

		LOG.debug("add keys size is : {}", keys.size());
		queue.addAll(keys);

		Jedis[] js = new Jedis[poolSize];
		for (int i = 0; i < poolSize; i++) {
			js[i] = pool.getResource();
		}

		for (int i = 0; i < poolSize; i++) {
			threadPool.execute(new RedisMacConsumer(conf, queue, js[i], pool));
		}

		threadPool.shutdown();
		pool.returnResource(jedis);
	}
}
