package com.sky.projects.design.behavior.observer.impl;

import com.sky.projects.design.behavior.observer.StateObserver;

/**
 * 八进制观察者
 * 
 * @author zealot
 */
public class OctalObserver implements StateObserver {

	@Override
	public void notify(int state) {
		// 将subject的状态更新为以八进制表示
		System.out.println("Octal String:" + Integer.toOctalString(state));
	}
}
