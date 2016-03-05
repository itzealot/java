package com.zt.design.behavior.command;

/**
 * 命令的实现类，执行Stock 的 sell 方法
 * 
 * @author zengtao
 *
 */
public class SellStock implements Order {
	private Stock stock;

	public SellStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public void execute() {
		stock.sell();
	}
}