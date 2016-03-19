package com.zt.design.behavior.observer;

/**
 * 八进制观测者
 * 
 * @author zengtao
 *
 */
public class OctalObserver extends Observer {

	public OctalObserver(Subject subject) {
		this.subject = subject;

		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	@Override
	public void update() {
		// 将subject的状态更新为以八进制表示
		System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
	}
}
