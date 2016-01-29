package com.zt.design.behavior.command;

/**
 * 创建一个请求类
 * 
 * @author zengtao
 *
 */
public class Stock {

	private String name = "ABC";
	private int quantity = 10;

	public void buy() {
		System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity
				+ " ] bought");
	}

	public void sell() {
		System.out.println("Stock [ Name: " + name + ",Quantity: " + quantity
				+ " ] sold");
	}
}