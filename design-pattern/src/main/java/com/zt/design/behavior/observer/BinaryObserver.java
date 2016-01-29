package com.zt.design.behavior.observer;

/**
 * class BinaryObserver extends Observer.<br />
 * 创建实体观察者类-二进制观测者
 * 
 * @author zengtao
 *
 */
public class BinaryObserver extends Observer {
	/**
	 * 创建对象的时候，调用this.subject.attach(this) method.<br />
	 * 
	 * @param subject
	 */
	public BinaryObserver(Subject subject) {
		this.subject = subject;

		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	/**
	 * 二进制
	 */
	@Override
	public void update() {
		// 将subject的状态更新为以二进制表示
		System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
	}
}