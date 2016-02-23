package com.zt.test.zookeeper.service.impl;

import java.util.Collection;

import com.zt.test.zookeeper.service.ZkService;

/**
 * Zookeeper crud操作的实现类
 * 
 * @author zt
 *
 * @param <T>
 */
public class ZkServiceImpl<T> implements ZkService<T> {

	@Override
	public void add(T t, String... paths) {

	}

	@Override
	public void update(T t, String... paths) {

	}

	@Override
	public void delete(T t, String... paths) {

	}

	@Override
	public T get(String path) {
		return null;
	}

	@Override
	public T get(String... paths) {
		return null;
	}

	@Override
	public Collection<T> findAll(String... paths) {
		return null;
	}

}
