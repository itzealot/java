package com.sky.projects.design.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用者
 * 
 * @author zealot
 */
public class OrderHolder {

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
	 * 执行命令列表所有命令，并清除命令列表
	 */
	public void placeOrders() {
		for (Order order : orders) {
			order.execute();
		}

		// 清除命令列表
		orders.clear();
	}
}