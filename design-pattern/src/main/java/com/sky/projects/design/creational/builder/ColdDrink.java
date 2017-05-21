package com.sky.projects.design.creational.builder;

/**
 * 冷饮(Cold drink)实现 Item 接口的抽象类
 * 
 * @author zealot
 *
 */
public abstract class ColdDrink implements Item {

	@Override
	public Packing packing() {
		// 冷饮的打包方式是装瓶
		return new Bottle();
	}

	public abstract float price();
}