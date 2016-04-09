package com.sky.projects.jdk.cache;

import java.util.Random;

public class GetThread implements Runnable {
	private Cache cache;
	private String name;

	public GetThread(Cache cache, String name) {
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
			String key = "cache" + new Random().nextInt(10);
			Object obj = cache.get(key);
			System.out.println(name + " To Get Data : key=" + key + ", value="
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
