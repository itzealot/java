package com.zt.design.behavior.command;

/**
 * 请求类
 * 
 * @author zengtao
 *
 */
public class Stock {

	// 请求名称
	private String name = "ABC";

	// 请求质量
	private int quantity = 10;

	/**
	 * 购买
	 */
	public void buy() {
		System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
	}

	/**
	 * 出售
	 */
	public void sell() {
		System.out.println("Stock [ Name: " + name + ",Quantity: " + quantity + " ] sold");
	}
}