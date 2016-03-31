package com.sky.projects.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Redis 工具类
 * 
 * @author zt
 */
public class Rediss {

	// Redis服务器IP
	private static String ADDR_ARRAY = Protocol.DEFAULT_HOST;

	// Redis的端口号
	private static int PORT = Protocol.DEFAULT_PORT;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 100;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 10;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 30 * 1000;

	// 超时时间
	private static int TIMEOUT = 10 * 1000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	// Jedis 连接池
	private static JedisPool jedisPool = null;

	private static Object lock2 = new Object();

	private static final Object lock = new Object();

	// redis 过期时间,以秒为单位
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		String[] address = ADDR_ARRAY.split(",");

		checkArray(address);

		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);

		try {
			jedisPool = new JedisPool(config, address[0], PORT, TIMEOUT);
		} catch (Exception e) {
			try {
				// 如果第一个IP异常，则访问第二个IP
				jedisPool = new JedisPool(config, address[0], PORT, TIMEOUT);
			} catch (Exception e2) {
				// TODO
			}
		}
	}

	private static <T> void checkNotNull(T[] array) {
		if (array == null) {
			throw new IllegalArgumentException("array must not be null");
		}
	}

	private static <T> void checkArray(T[] array) {
		checkNotNull(array);

		if (array.length == 0) {
			throw new IllegalArgumentException("array must not be empty");
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static void poolInit() {
		if (jedisPool == null) {
			synchronized (lock) {
				initialPool();
			}
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public static Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}

		Jedis jedis = null;

		synchronized (lock2) {
			jedis = jedisPool.getResource();
		}

		return jedis;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void release(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 设置 String
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {

		try {
			value = (value == null ? "" : value);
			getJedis().set(key, value);
		} catch (Exception e) {
			// TODO
		}
	}

	/**
	 * 设置 过期时间
	 * 
	 * @param key
	 * @param seconds
	 *            以秒为单位
	 * @param value
	 */
	public static void set(String key, int seconds, String value) {
		try {
			value = (value == null ? "" : value);
			getJedis().setex(key, seconds, value);
		} catch (Exception e) {
			// TODO
		}
	}

	/**
	 * 获取String值
	 * 
	 * @param key
	 * @return value
	 */
	public static String get(String key) {
		Jedis redis = getJedis();

		if (redis == null || !redis.exists(key)) {
			return null;
		}

		return redis.get(key);
	}

}