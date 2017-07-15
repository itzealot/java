package com.sky.projects.design.behavior.strategy;

/**
 * 策略接口(策略模式的核心)
 * 
 * @author zealot
 */
public interface Strategy {

	/**
	 * 根据传入的两个 int 值，根据实现类返回处理后的值
	 * 
	 * @param num1
	 * @param num2
	 * @return 返回操作结果
	 */
	int operation(int num1, int num2);
}
