package com.sky.projects.design.behavior.observer;

/**
 * 观察者接口
 * 
 * @author zealot
 */
public interface StateObserver {

	/**
	 * 状态发生改变后进行通知
	 * 
	 * @param state
	 *            发生改变后的状态
	 */
	void notify(int state);
}