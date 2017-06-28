package com.sky.projects.jdk.util;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Properties 本质上是一个HashTable，每次读写都会加锁，所以不支持频繁的System.getProperty(name)来检查系统内容变化
 * 因此扩展了一个ListenableProperties, 在其所关心的属性变化时进行通知.
 * 
 * @author zealot
 *
 */
public class ListenableProperties extends Properties implements PropertiesListenerFactory {

	private static final long serialVersionUID = 5311206864671369034L;

	public ListenableProperties(Properties properties) {
		super(properties);
	}

	// 监听者列表
	protected List<PropertiesListener> listeners = new CopyOnWriteArrayList<>();

	@Override
	public void attach(PropertiesListener listener) {
		listeners.add(listener);
	}

	@Override
	public void detach(PropertiesListener listener) {
		listeners.remove(listener);
	}

	@Override
	public synchronized Object setProperty(String key, String value) {
		Object old = put(key, value);

		for (PropertiesListener listener : listeners) {
			if (key.equals(listener.getKey())) { // 监听的key变化，通知观察者
				listener.notify(key, value);
			}
		}

		return old;
	}

}
