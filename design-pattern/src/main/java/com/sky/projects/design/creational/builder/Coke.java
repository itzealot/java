package com.sky.projects.design.creational.builder;

/**
 * 冷饮ColdDrink 实现类，可口可乐Coke
 * 
 * @author zealot
 *
 */
public class Coke extends ColdDrink {

	@Override
	public float price() {
		return 30.0f;
	}

	@Override
	public String name() {
		// 可口可乐
		return "Coke";
	}
}