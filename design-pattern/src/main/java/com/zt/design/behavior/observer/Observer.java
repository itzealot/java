package com.zt.design.behavior.observer;

/**
 * 抽象的观察者，专门用于继承
 * 
 * @author a
 *
 */
public abstract class Observer {
	// 主题
	protected Subject subject;

	// 更新
	public abstract void update();
}