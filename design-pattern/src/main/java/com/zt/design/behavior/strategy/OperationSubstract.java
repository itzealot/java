package com.zt.design.behavior.strategy;

/**
 * class OperationSubstract implements Strategy
 * 
 * @author zengtao
 *
 */
public class OperationSubstract implements Strategy {
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}