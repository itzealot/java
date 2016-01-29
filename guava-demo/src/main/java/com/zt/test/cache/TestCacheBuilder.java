package com.zt.test.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class TestCacheBuilder {

	@Test
	public void TestLoadingCache() throws Exception {

		// To build LoadingCache<K, V> by CacheBuilder
		LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(
				new CacheLoader<String, String>() {

					// load value by key if the key don't contain
					@Override
					public String load(String key) throws Exception {
						String strProValue = "hello " + key + "!";
						return strProValue;
					}

				});

		// To get the value byu get method
		System.out.println("jerry value: " + cache.get("jerry"));

		// To get the value by getUnchecked method
		System.out.println("peida value: " + cache.getUnchecked("peida"));

		// To put LoadingCache into by <key, value>
		cache.put("harry", "ssdded");
		System.out.println("harry value: " + cache.get("harry"));
	}

	@Test
	public void testcallableCache() throws Exception {

		// To build Cache<String, String>
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).build();

		// To put into Cache<String, String> by <K, V>
		String resultVal = cache.get("jerry", new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "jerry" + "!";
				return strProValue;
			}
		});

		// To get the result
		System.out.println("jerry value : " + resultVal);

		// To put into Cache<String, String> by <K, V>
		resultVal = cache.get("peida", new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "peida" + "!";
				return strProValue;
			}
		});

		// To get the result
		System.out.println("peida value : " + resultVal);
	}

	/***
	 * cache的参数说明：<br />
	 * 
	 * 　　回收的参数：<br />
	 * 　　1. 大小的设置：CacheBuilder.maximumSize(long) CacheBuilder.weigher(Weigher)
	 * CacheBuilder.maxumumWeigher(long).<br />
	 * 
	 * 　　2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long,
	 * TimeUnit).<br />
	 * 
	 * 　　3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()
	 * CacheBuilder.softValues()<br />
	 * 
	 * 　　4. 明确的删除：invalidate(key) invalidateAll(keys) invalidateAll().<br />
	 * 
	 * 　　5. 删除监听器：CacheBuilder.removalListener(RemovalListener).<br />
	 * 　　
	 * 
	 * 　　refresh机制：<br />
	 * 　　1. LoadingCache.refresh(K) 在生成新的value的时候，旧的value依然会被使用.<br />
	 * 　　2. CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value.<br />
	 * 　　3. CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache.<br />
	 * 
	 */

	/**
	 * 不需要延迟处理(泛型的方式封装)
	 * 
	 * @return
	 */
	public <K, V> LoadingCache<K, V> cached(CacheLoader<K, V> cacheLoader) {
		LoadingCache<K, V> cache = CacheBuilder.newBuilder().maximumSize(2)
				.weakKeys().softValues()
				.refreshAfterWrite(120, TimeUnit.SECONDS)
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.removalListener(new RemovalListener<K, V>() {
					@Override
					public void onRemoval(RemovalNotification<K, V> rn) {
						System.out.println(rn.getKey() + "被移除");

					}
				}).build(cacheLoader);
		return cache;
	}

	/**
	 * 通过key获取value 调用方式 commonCache.get(key) ; return String
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public LoadingCache<String, String> commonCache(final String key)
			throws Exception {
		LoadingCache<String, String> commonCache = cached(new CacheLoader<String, String>() {
			@Override
			public String load(String key) throws Exception {
				return "hello " + key + "!";
			}
		});
		return commonCache;
	}

	@Test
	public void testCache() throws Exception {
		LoadingCache<String, String> commonCache = commonCache("peida");
		System.out.println("peida: " + commonCache.get("peida"));

		commonCache.get("harry");
		System.out.println("harry: " + commonCache.get("harry"));

		commonCache.get("lisa");
		System.out.println("lisa: " + commonCache.get("lisa"));
	}

	/**
	 * 基于泛型的Callable Cache实现
	 */
	private static Cache<String, String> cacheFormCallable = null;

	/**
	 * 对需要延迟处理的可以采用这个机制；(泛型的方式封装)
	 * 
	 * @param <K>
	 * @param <V>
	 * @param key
	 * @param callable
	 * @return V
	 * @throws Exception
	 */
	public static <K, V> Cache<K, V> callableCached() throws Exception {
		Cache<K, V> cache = CacheBuilder.newBuilder().maximumSize(10000)
				.expireAfterWrite(10, TimeUnit.MINUTES).build();
		return cache;
	}

	private String getCallableCache(final String userName) {
		try {
			// Callable只有在缓存值不存在时，才会调用
			return cacheFormCallable.get(userName, new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println(userName + " from db");
					return "hello " + userName + "!";
				}
			});
		} catch (ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void testCallableCache() throws Exception {
		final String u1name = "peida";
		final String u2name = "jerry";
		final String u3name = "lisa";
		cacheFormCallable = callableCached();
		System.out.println("peida: " + getCallableCache(u1name));
		System.out.println("jerry: " + getCallableCache(u2name));
		System.out.println("lisa: " + getCallableCache(u3name));
		System.out.println("peida: " + getCallableCache(u1name));

	}
}
