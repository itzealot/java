package com.sky.projects.design.creational.builder;

/**
 * 百事可乐 Pepsi 实现 ColdDrink 接口
 * 
 * @author zealot
 *
 */
public class Pepsi extends ColdDrink {

	@Override
	public float price() {
		return 35.0f;
	}

	@Override
	public String name() {
		// 该项名称是百事可乐
		return "Pepsi";
	}
}
