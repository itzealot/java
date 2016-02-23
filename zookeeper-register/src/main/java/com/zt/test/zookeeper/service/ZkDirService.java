package com.zt.test.zookeeper.service;

import java.util.Collection;

/**
 * 目录接口
 * 
 * @author zt
 *
 * @param <T>
 */
public interface ZkDirService<T> {

	public T getParent(T t);

	public Collection<T> getChildren(T t);
}
