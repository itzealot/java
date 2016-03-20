package com.zt.design.creational.builder;

/**
 * 冷饮（Cold drink）实现 Item 接口的抽象类
 * 
 * @author zengtao
 *
 */
public abstract class ColdDrink implements Item {

	public Packing packing() {
		// 冷饮的打包方式是装瓶
		return new Bottle();
	}

	public abstract float price();
}