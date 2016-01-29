package com.zt.design.behavior.strategy;

/**
 * class OperationMultiply implements Strategy
 * 
 * @author zengtao
 *
 */
public class OperationMultiply implements Strategy {
	/**
	 * 乘法操作
	 */
	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}
}