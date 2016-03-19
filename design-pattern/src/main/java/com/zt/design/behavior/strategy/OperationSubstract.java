package com.zt.design.behavior.strategy;

/**
 * 减法策略实现类
 * 
 * @author zengtao
 *
 */
public class OperationSubstract implements Strategy {
	private static final long serialVersionUID = -6483650660318337201L;

	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}