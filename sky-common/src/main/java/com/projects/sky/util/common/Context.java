package com.projects.sky.util.common;

import java.util.Map;

public interface Context {

	/**
	 * 获取存放在服务上下文中的数据
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key);

	/**
	 * 存放数据到上下文中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Map<String, Object> put(String key, Object value);
}
