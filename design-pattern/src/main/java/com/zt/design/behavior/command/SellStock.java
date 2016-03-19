package com.zt.design.behavior.command;

/**
 * 命令的实现类，执行 Stock 的 sell 方法
 * 
 * @author zengtao
 *
 */
public class SellStock implements Order {
	private Stock stock;

	public SellStock(Stock stock) {
		this.stock = stock;
	}

	public void execute() {
		stock.sell();
	}
}