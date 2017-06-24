package com.sky.projects.jdk.observer;

/**
 * 观察者接口，定义了观察者的基本方法。当依赖状态发生改变时，主题接口就会调用观察者的update方法
 * 
 * @author zealot
 *
 */
public interface Observerable {

	/**
	 * 根据相应的事件更新观察者信息
	 * 
	 * @param event
	 */
	void update(Event event);
}
