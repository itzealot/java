package com.zt.test.zookeeper.service;

import java.util.Collection;

/**
 * 基于 Zookeeper 的crud接口
 * 
 * @author zt
 *
 * @param <T>
 */
public interface ZkService<T> {

	public void add(T t);

	public void update(T t);

	public void delete(T t);

	public T get(String path);

	Collection<T> findAll();

}
