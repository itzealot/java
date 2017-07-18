package com.sky.projects.design.behavior.strategy;

/**
 * Strategy代理类(根据传入的策略实现类，执行策略的方法)
 * 
 * @author zealot
 */
public class StrategyDelegate implements Strategy {

	private Strategy strategy;

	public StrategyDelegate(Strategy strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public int operation(int num1, int num2) {
		return strategy.operation(num1, num2);
	}
}