package com.sky.projects.design.behavior.observer.impl;

import com.sky.projects.design.behavior.observer.StateObserver;

/**
 * 十六进制观察者
 * 
 * @author zealot
 */
public class HexaObserver implements StateObserver {

	@Override
	public void notify(int state) {
		// 将subject的状态更新为以十六进制表示
		System.out.println("Hex String:" + Integer.toHexString(state).toUpperCase());
	}
}
