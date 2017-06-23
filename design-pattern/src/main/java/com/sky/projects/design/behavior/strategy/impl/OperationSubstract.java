package com.sky.projects.design.behavior.strategy.impl;

import com.sky.projects.design.behavior.strategy.Strategy;

/**
 * 减法策略实现类
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
public class OperationSubstract implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}