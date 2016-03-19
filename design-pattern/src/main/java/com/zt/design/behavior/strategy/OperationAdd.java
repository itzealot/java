package com.zt.design.behavior.strategy;

/**
 * 加法策略实现类
 * 
 * @author zengtao
 *
 */
public class OperationAdd implements Strategy {
	private static final long serialVersionUID = -9175171076442850638L;

	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}