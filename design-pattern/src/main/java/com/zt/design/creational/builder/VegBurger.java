package com.zt.design.creational.builder;

/**
 * 创建扩展了 Burger 的实体类。<br />
 * 素食汉堡（Veg Burger）
 * 
 * @author zengtao
 *
 */
public class VegBurger extends Burger {

	/**
	 * 价格
	 */
	@Override
	public float price() {
		return 25.0f;
	}

	/**
	 * 名称
	 */
	public String name() {
		return "Veg Burger";
	}
}
