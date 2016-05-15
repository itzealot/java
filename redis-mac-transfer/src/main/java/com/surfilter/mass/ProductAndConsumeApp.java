package com.surfilter.mass;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.surfilter.mass.config.RedisMacConf;
import com.surfilter.mass.redis.pool.RedisPool;
import com.surfilter.mass.thread.RedisMacConsumer;
import com.surfilter.mass.thread.RedisMacProducter;

import redis.clients.jedis.Jedis;

/**
 * 多线程并发执行 Redis mac key 程序入口，生产者消费者模式
 * 
 * @author zt
 *
 */
public class ProductAndConsumeApp {

	private static final Logger LOG = LoggerFactory.getLogger(ProductAndConsumeApp.class);

	public static void main(String[] args) {
		RedisMacConf conf = RedisMacConf.getInstance();

		int poolSize = conf.getPoolSize();

		RedisPool pool = new RedisPool(conf.getHost(), conf.getPort(), 600 * 1000);

		Jedis jedis = pool.getResource();
		LOG.debug("host: {}, port: {}, Jedis: {}", conf.getHost(), conf.getPort(), jedis.ping());

		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

		// init BlockingQueue
		initQueue(conf.getDealMacPartern(), queue, pool);

		startConsume(poolSize, conf, queue, pool);

		pool.returnResource(jedis);
	}

	private static void startConsume(int poolSize, RedisMacConf conf, BlockingQueue<String> queue, RedisPool pool) {
		// 根据线程池大小创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);

		Jedis[] js = new Jedis[poolSize];
		for (int i = 0; i < poolSize; i++) {
			js[i] = pool.getResource();
		}

		for (int i = 0; i < poolSize; i++) {
			threadPool.execute(new RedisMacConsumer(conf, queue, js[i], pool));
		}

		threadPool.shutdown();
	}

	private static void initQueue(String dealPattern, BlockingQueue<String> queue, RedisPool pool) {
		Jedis[] js = new Jedis[16];
		for (int i = 0; i < 16; i++) {
			js[i] = pool.getResource();
		}

		ExecutorService threadPool = Executors.newFixedThreadPool(16);
		char[] prefixs = "0123456789ABCDEF".toCharArray();

		queue.addAll(js[0].keys(dealPattern + prefixs[0] + "*"));
		pool.returnResource(js[0]);

		for (int i = 1; i < 16; i++) {
			threadPool.execute(new RedisMacProducter(queue, dealPattern + prefixs[i] + "*", js[i], pool));
		}

		threadPool.shutdown();
	}
}
