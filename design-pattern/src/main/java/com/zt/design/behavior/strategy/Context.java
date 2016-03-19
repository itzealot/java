package com.zt.design.behavior.strategy;

/**
 * 根据传入的策略实现类，执行策略的方法
 * 
 * @author zt
 */
public class Context {

	// 策略接口
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 执行策略的方法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}