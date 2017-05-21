package com.sky.projects.design.behavior.command.impl;

import com.sky.projects.design.behavior.command.Order;
import com.sky.projects.design.behavior.command.Stock;

/**
 * 命令的实现类，执行 Stock 的 buy 方法
 * 
 * @author Broker
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