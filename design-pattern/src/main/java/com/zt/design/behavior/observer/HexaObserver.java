package com.zt.design.behavior.observer;

/**
 * class HexaObserver extends Observer.<br />
 * 创建实体观察者类-十六进制观测者
 * 
 * @author zengtao
 *
 */
public class HexaObserver extends Observer {

	/**
	 * 创建对象的时候，调用this.subject.attach(this) method.<br />
	 * 
	 * @param subject
	 */
	public HexaObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	/**
	 * 十六进制
	 */
	@Override
	public void update() {
		// 将subject的状态更新为以十六进制表示
		System.out.println("Hex String: "
				+ Integer.toHexString(subject.getState()).toUpperCase());
	}
}
