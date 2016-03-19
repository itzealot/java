package com.zt.design.behavior.observer;

/**
 * 二进制观测者
 * 
 * @author zengtao
 *
 */
public class BinaryObserver extends Observer {

	public BinaryObserver(Subject subject) {
		this.subject = subject;

		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	@Override
	public void update() {
		// 将subject的状态更新为以二进制表示
		System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
	}
}