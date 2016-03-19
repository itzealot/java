package com.zt.design.behavior.strategy;

/**
 * 乘法策略实现类
 * 
 * @author zengtao
 *
 */
public class OperationMultiply implements Strategy {
	private static final long serialVersionUID = -8579859818800348853L;

	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}
}