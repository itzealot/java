package com.sky.projects.design.creational.builder;

/**
 * 鸡肉汉堡(Chicken Burger)，汉堡 Burger 的实现类
 * 
 * @author zealot
 *
 */
public class ChickenBurger extends Burger {

	@Override
	public float price() {
		return 50.5f;
	}

	@Override
	public String name() {
		return "Chicken Burger";
	}
}