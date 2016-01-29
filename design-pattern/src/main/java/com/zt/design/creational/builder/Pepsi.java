package com.zt.design.creational.builder;

/**
 * ColdDrink 包含了百事可乐Pepsi，且含有相应的价格
 * 
 * @author zengtao
 *
 */
public class Pepsi extends ColdDrink {

	/**
	 * 价格
	 */
	@Override
	public float price() {
		return 35.0f;
	}

	/**
	 * 该项名称是百事可乐
	 */
	public String name() {
		return "Pepsi";
	}
}
