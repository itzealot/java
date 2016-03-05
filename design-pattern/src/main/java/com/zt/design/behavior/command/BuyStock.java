package com.zt.design.behavior.command;

/**
 * 命令的实现类，执行 Stock 的 buy 方法
 * 
 * @author zengtao
 *
 */
public class BuyStock implements Order {
	private Stock stock;

	public BuyStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public void execute() {
		stock.buy();
	}
}