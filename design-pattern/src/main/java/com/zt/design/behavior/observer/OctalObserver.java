package com.zt.design.behavior.observer;

/**
 * class OctalObserver extends Observer.<br />
 * 创建实体观察者类-八进制观测者
 * 
 * @author zengtao
 *
 */
public class OctalObserver extends Observer {
	/**
	 * 创建对象的时候，调用this.subject.attach(this) method.<br />
	 * 
	 * @param subject
	 */
	public OctalObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	/**
	 * 八进制
	 */
	@Override
	public void update() {
		// 将subject的状态更新为以八进制表示
		System.out.println("Octal String: "
				+ Integer.toOctalString(subject.getState()));
	}
}
