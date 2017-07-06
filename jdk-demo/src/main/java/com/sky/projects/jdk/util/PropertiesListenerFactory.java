package com.sky.projects.jdk.util;

public interface PropertiesListenerFactory {

	/**
	 * 添加观察者
	 * 
	 * @param listener
	 */
	void attach(PropertiesListener listener);

	/**
	 * 删除观察者
	 * 
	 * @param listener
	 */
	void detach(PropertiesListener listener);

	/**
	 * 当发生 setProperty 时通知观察者
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Object setProperty(String key, String value);

}
