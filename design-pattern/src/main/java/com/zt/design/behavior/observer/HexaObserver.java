package com.zt.design.behavior.observer;

/**
 * 十六进制观测者
 * 
 * @author zengtao
 *
 */
public class HexaObserver extends Observer {

	public HexaObserver(Subject subject) {
		this.subject = subject;

		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	@Override
	public void update() {
		// 将subject的状态更新为以十六进制表示
		System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
	}
}
