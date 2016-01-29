package com.zt.design.behavior.strategy;

/**
 * class OperationAdd implements Strategy
 * 
 * @author zengtao
 *
 */
public class OperationAdd implements Strategy {
	/**
	 * 加法操作
	 */
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}