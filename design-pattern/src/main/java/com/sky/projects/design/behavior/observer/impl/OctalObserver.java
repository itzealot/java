package com.sky.projects.design.behavior.observer.impl;

import com.sky.projects.design.behavior.observer.Observer;
import com.sky.projects.design.behavior.observer.Subject;

/**
 * 八进制观测者
 * 
 * @author zealot
 *
 */
public class OctalObserver extends Observer {

	public OctalObserver(Subject subject) {
		super(subject);
		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	@Override
	public void update() {
		// 将subject的状态更新为以八进制表示
		System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
	}
}
