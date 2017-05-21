package com.sky.projects.design.creational.builder;

/**
 * 素食汉堡(Veg Burger)，汉堡 Burger 的实现类
 * 
 * @author zealot
 *
 */
public class VegBurger extends Burger {

	@Override
	public float price() {
		return 25.0f;
	}

	@Override
	public String name() {
		return "Veg Burger";
	}
}
