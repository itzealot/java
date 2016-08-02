package com.sky.projects.util;

import java.util.Collection;
import java.util.Set;

public interface SkyContext {

	/**
	 * 获取存放在服务上下文中的数据
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key);

	/**
	 * 存放数据到上下文中，返回旧数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Object put(String key, Object value);

	/**
	 * 获取所有的 key
	 * 
	 * @return
	 */
	public Set<String> keys();

	/**
	 * 获取所有的 key
	 * 
	 * @return
	 */
	public Set<String> keys(KeyFilter filter);

	/**
	 * 获取所有的 value
	 * 
	 * @return
	 */
	public Collection<Object> values();

	static interface KeyFilter {
		public boolean accept(String pattern);
	}
}
