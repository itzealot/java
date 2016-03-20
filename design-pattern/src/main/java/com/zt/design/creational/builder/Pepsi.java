package com.zt.design.creational.builder;

/**
 * 百事可乐 Pepsi 实现 ColdDrink 接口
 * 
 * @author zengtao
 *
 */
public class Pepsi extends ColdDrink {

	public float price() {
		return 35.0f;
	}

	public String name() {
		// 该项名称是百事可乐
		return "Pepsi";
	}
}
