package com.sky.projects.design.behavior.command.impl;

import com.sky.projects.design.behavior.command.Order;
import com.sky.projects.design.behavior.command.Stock;

/**
 * 命令的实现类，执行 Stock 的 sell 方法
 * 
 * @author zealot
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