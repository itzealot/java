package com.sky.projects.jdk.cache;

import java.io.Serializable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheTest extends TestCase {
	public EhCacheTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(EhCacheTest.class);
	}

	public void testEternal() throws InterruptedException {
		CacheManager cacheManager = CacheManager.create();
		// 永远不过期，内存缓存，除非jvm关掉了
		Cache cache = new Cache("cache", 100, false, true, 60 * 5, 10);
		cacheManager.addCache(cache);

		for (int i = 0; i < 100; i++)
			put(cache, "" + i, "value is: " + i);

		for (int i = 0; i < 100; i++) {
			System.out.println(cache.get("" + i).getObjectKey());
			Thread.sleep(10);
		}
	}

	public static void put(Cache cache, Serializable key, Serializable value) {
		cache.put(new Element(key, value));
	}
}
