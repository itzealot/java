package com.sky.projects.design.behavior.observer.impl;

import com.sky.projects.design.behavior.observer.Observer;
import com.sky.projects.design.behavior.observer.Subject;

/**
 * 十六进制观测者
 * 
 * @author zealot
 */
public class HexaObserver extends Observer {

	public HexaObserver(Subject subject) {
		super(subject);
		// 给当前的主题添加观察者
		this.subject.attach(this);
	}

	@Override
	public void update() {
		// 将subject的状态更新为以十六进制表示
		System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
	}
}
