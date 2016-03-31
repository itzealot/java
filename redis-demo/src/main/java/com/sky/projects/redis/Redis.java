package com.sky.projects.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

public class Redis {

	public static final int DEFAULT_PORT = Protocol.DEFAULT_PORT;
	public static final String DEFAULT_HOST = Protocol.DEFAULT_HOST;

	private Jedis jedis = null;

	private String host;

	private int port;

	private boolean connected = false;

	private static Redis instance = null;

	private static final Object lock = new Object();

	private Redis(String host, int port) {
		this.host = host;
		this.port = port;

		jedis = new Jedis(host, port);

		connected = true;
	}

	public static Redis getInstance(String host, int port) {
		if (instance == null) {
			synchronized (lock) {
				instance = new Redis(host, port);
			}
		}

		if (!instance.connected) {
			instance.connect();
		}

		return instance;
	}

	public void connect() {
		if (!connected) {
			this.jedis = null;

			this.jedis = new Jedis(this.host, this.port);
		}

		connected = true;
	}

	public void close() {
		if (connected) {
			jedis.close();
			this.connected = false;
		}
	}

	public Set<String> keys() {
		return keys("*");
	}

	public Set<String> keys(String pattern) {
		if (connected) {
			return jedis.keys(pattern);
		}

		return new HashSet<String>();
	}

	public String get(String key) {
		if (connected) {
			return jedis.get(key);
		}

		return null;
	}

	public void set(String key, String value) {
		if (connected) {
			jedis.set(key, value);
		}
	}

	public void lpush(String key, String... strings) {
		if (connected) {
			jedis.lpush(key, strings);
		}
	}

	public List<String> lrange(String key, long start, long end) {
		if (connected) {
			return jedis.lrange(key, start, end);
		}

		return new ArrayList<String>();
	}

	public String ping() {
		if (jedis != null) {
			return jedis.ping();
		}

		return null;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public boolean isConnected() {
		return connected;
	}
}
