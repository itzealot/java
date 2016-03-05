package com.zt.design.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用类
 * 
 * @author zengtao
 */
public class Broker {

	// 命令列表
	private List<Order> orders = new ArrayList<Order>();

	/**
	 * 添加命令到命令列表
	 * 
	 * @param order
	 */
	public void takeOrder(Order order) {
		orders.add(order);
	}

	/**
	 * 执行命令列表，并清除命令列表
	 */
	public void placeOrders() {
		for (Order order : orders) {
			order.execute();
		}

		orders.clear();
	}
}