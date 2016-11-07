package com.sky.projects.jdk.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheTest {
	public static void main(String[] args) {
		CacheManager cacheManager = CacheManager.create();
		// 永远不过期，内存缓存，除非jvm关掉了
		Cache cache = new Cache("cache", 100, false, true, 60 * 5, 10);
		cacheManager.addCache(cache);

		for (int i = 0; i < 100; i++)
			put(cache, "" + i, "value is: " + i);

		for (int i = 0; i < 100; i++) {
			System.out.println(cache.get("" + i).getObjectKey());
		}
	}

	public static void put(Cache cache, Serializable key, Serializable value) {
		cache.put(new Element(key, value));
	}
}
