package com.zt.design.behavior.strategy;

public class Context {
	private Strategy strategy;

	// 构造对象时，指定传入的Strategy对象
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 根据Strategy strategy 对象确定执行何种操作
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}