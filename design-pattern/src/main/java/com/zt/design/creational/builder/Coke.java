package com.zt.design.creational.builder;

/**
 * ColdDrink 包含了可口可乐Coke，且含有相应的价格
 * 
 * @author zengtao
 *
 */
public class Coke extends ColdDrink {

	/**
	 * 价格
	 */
	@Override
	public float price() {
		return 30.0f;
	}

	/**
	 * 该项名称是可口可乐
	 */
	public String name() {
		return "Coke";
	}
}