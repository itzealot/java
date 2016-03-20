package com.zt.design.creational.builder;

/**
 * 冷饮ColdDrink 实现类，可口可乐Coke
 * 
 * @author zengtao
 *
 */
public class Coke extends ColdDrink {

	public float price() {
		return 30.0f;
	}

	public String name() {
		// 可口可乐
		return "Coke";
	}
}