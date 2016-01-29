package com.sky.projects.test.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

	/**
	 * 缓冲的 Map cache. <br />
	 */
	private Map<String, Object> cache = new HashMap<String, Object>();

	// 使用读写锁
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * 使用读写锁从缓存cache map中按照String key读取数据
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {

		// 1. 上读锁，其他读进程可以进行以下操作
		lock.readLock().lock();
		Object object = null;
		try {
			// 2. 从cache Map 中按String key读取数据
			object = cache.get(key);

			// 3. cache Map 中中没有相应数据，从数据库中读取数据并写入到cache Map中
			if (object == null) {

				// 3.1 释放掉以前上的读锁
				lock.readLock().unlock();

				// 3.2 上写锁，即为独占锁，其他读线程、写线程都不能进行读写
				lock.writeLock().lock();
				try {
					// 3.3 相当于没获取到值时，从数据库中查找

					// 若数据为null，需要从数据库中查找并写入缓存cache map中
					if (object == null) {
						object = "aaa";
					}
				} finally {

					// 3.4 释放写锁(独占锁)
					lock.writeLock().unlock();
				}

				// 3.5 恢复原来的读锁，读取数据完毕，其他线程可以进行读
				lock.readLock().lock();
			}
		} finally {
			// 4. 关闭读锁
			lock.readLock().unlock();
		}
		return object;
	}

	/**
	 * To put data into Cache
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Map<String, Object> put(String key, Object value) {

		try {

			// 上写锁
			lock.writeLock().lock();

			// 添加数据
			cache.put(key, value);
		} finally {

			// 关闭写锁
			lock.writeLock().unlock();
		}

		return cache;
	}
}
