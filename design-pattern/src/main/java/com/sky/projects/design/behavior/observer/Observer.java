package com.sky.projects.design.behavior.observer;

/**
 * 抽象的观察者，专门用于继承
 * 
 * @author zealot
 *
 */
public abstract class Observer {
	// 主题
	protected Subject subject;

	public Observer(Subject subject) {
		this.subject = subject;
	}

	/**
	 * 更新观测者的信息
	 */
	public abstract void update();
}