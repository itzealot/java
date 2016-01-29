package com.zt.design.creational.builder;

/**
 * 鸡肉汉堡（Chicken Burger）<br />
 * 
 * @author zengtao
 *
 */
public class ChickenBurger extends Burger {

	/**
	 * 价格
	 */
	@Override
	public float price() {
		return 50.5f;
	}

	/**
	 * 名称
	 */
	public String name() {
		return "Chicken Burger";
	}
}