package com.sky.projects.design.behavior.observer.impl;

import com.sky.projects.design.behavior.observer.Observer;
import com.sky.projects.design.behavior.observer.Subject;

/**
 * 二进制观测者
 * 
 * @author zealot
 *
 */
public class BinaryObserver extends Observer {

	public BinaryObserver(Subject subject) {
		super(subject);
		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	@Override
	public void update() {
		// 将subject的状态更新为以二进制表示
		System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
	}
}