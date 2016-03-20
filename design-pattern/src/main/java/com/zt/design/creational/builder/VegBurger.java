package com.zt.design.creational.builder;

/**
 * 素食汉堡(Veg Burger)，汉堡 Burger 的实现类
 * 
 * @author zengtao
 *
 */
public class VegBurger extends Burger {

	public float price() {
		return 25.0f;
	}

	public String name() {
		return "Veg Burger";
	}
}
