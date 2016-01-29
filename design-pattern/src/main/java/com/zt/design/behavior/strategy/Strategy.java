package com.zt.design.behavior.strategy;

import java.io.Serializable;

/**
 * 进行操作的接口
 * 
 * @author zengtao
 *
 */
public interface Strategy extends Serializable {
	/**
	 * 根据不同的要求实现不同的运算
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int doOperation(int num1, int num2);
}
