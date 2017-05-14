package com.sky.projects.design.behavior.strategy;

import java.io.Serializable;

/**
 * 策略接口
 * 
 * @author zealot
 *
 */
public interface Strategy extends Serializable {

	/**
	 * 根据传入的两个 int 值进行处理
	 * 
	 * @param num1
	 * @param num2
	 * @return 返回操作结果
	 */
	public int doOperation(int num1, int num2);
}
