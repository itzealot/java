package com.sky.projects.jdk.cache;

import java.util.Random;

public class PutThread implements Runnable {

	private Cache cache;
	private String name;

	public PutThread(Cache cache, String name) {
		this.cache = cache;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			// sleep 1s
			try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// To put data
			int value = new Random().nextInt(10);
			String key = "cache" + value;
			cache.put(key, key);
			Object obj = key;
			System.out.println(name + " To Put Data : key=" + key + ", value="
					+ obj);
		}
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

}
