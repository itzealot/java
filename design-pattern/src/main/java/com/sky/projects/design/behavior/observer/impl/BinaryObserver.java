package com.sky.projects.design.behavior.observer.impl;

import com.sky.projects.design.behavior.observer.StateObserver;

/**
 * 二进制观察者
 * 
 * @author zealot
 */
public class BinaryObserver implements StateObserver {

	@Override
	public void notify(int state) {
		// 将subject的状态更新为以二进制表示
		System.out.println("Binary String:" + Integer.toBinaryString(state));
	}
}