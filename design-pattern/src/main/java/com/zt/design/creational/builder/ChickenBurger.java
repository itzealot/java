package com.zt.design.creational.builder;

/**
 * 鸡肉汉堡(Chicken Burger)，汉堡 Burger 的实现类
 * 
 * @author zengtao
 *
 */
public class ChickenBurger extends Burger {

	public float price() {
		return 50.5f;
	}

	public String name() {
		return "Chicken Burger";
	}
}