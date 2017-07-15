package com.sky.projects.design.behavior.strategy.impl;

import com.sky.projects.design.behavior.strategy.Strategy;

/**
 * 加法策略实现类
 * 
 * @author zealot
 */
public class OperationAdd implements Strategy {

	@Override
	public int operation(int num1, int num2) {
		return num1 + num2;
	}
}