package com.sky.projects.jdk.util;

public interface PropertiesListener {

	/**
	 * 监听的 key
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * 通知观察者
	 * 
	 * @param key
	 *            变化的key
	 * @param value
	 *            变化的value
	 */
	void notify(String key, String value);
}
