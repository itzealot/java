package com.sky.projects.jdk.thread.cache.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.sky.projects.jdk.thread.cache.Cache;

/**
 * SynReadWriteHashMapCache
 * 
 * @author zealot
 *
 * @param <K>
 * @param <V>
 */
public class SynReadWriteHashMapCache<K, V> implements Cache<K, V> {

	private final Map<Object, Object> cache;

	private final ReentrantReadWriteLock lock;

	public SynReadWriteHashMapCache() {
		this.cache = new HashMap<>();
		this.lock = new ReentrantReadWriteLock();
	}

	public SynReadWriteHashMapCache(int capacity) {
		this.cache = new HashMap<>(capacity);
		this.lock = new ReentrantReadWriteLock();
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		try {
			lock.writeLock().lock();
			return (V) cache.put(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		try {
			lock.readLock().lock();
			return (V) cache.get(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
			lock.readLock().lock();
			return (Set<K>) cache.keySet();
		} finally {
			lock.readLock().unlock();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		try {
			lock.readLock().lock();
			return (Collection<V>) cache.values();
		} finally {
			lock.readLock().unlock();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putIfAbsent(K key, V value) {
		V old = null;
		try {
			lock.readLock().lock(); // 上读锁
			old = (V) cache.get(key);
		} finally {
			lock.readLock().unlock(); // 释放读锁
		}

		if (old == null) { // not exists
			try {
				lock.writeLock().lock(); // 写入数据，上写锁
				cache.putIfAbsent(key, value); // 需要重新判断是否存在
			} finally {
				lock.writeLock().unlock(); // 释放写锁
			}
		}
	}

}
