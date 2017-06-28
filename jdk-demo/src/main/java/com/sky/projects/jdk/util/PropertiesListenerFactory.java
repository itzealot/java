package com.sky.projects.jdk.util;

public interface PropertiesListenerFactory {

	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	void attach(PropertiesListener observer);

	/**
	 * 删除观察者
	 * 
	 * @param observer
	 */
	void detach(PropertiesListener observer);

	/**
	 * 当发生 setProperty 时通知观察者
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Object setProperty(String key, String value);

}
