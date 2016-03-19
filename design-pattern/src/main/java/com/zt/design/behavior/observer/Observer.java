package com.zt.design.behavior.observer;

/**
 * 抽象的观察者，专门用于继承
 * 
 * @author zt
 *
 */
public abstract class Observer {
	// 主题
	protected Subject subject;

	/**
	 * 更新观测者的信息
	 */
	public abstract void update();
}