package com.zt.design.creational.builder;

/**
 * abstract class ColdDrink implements Item.<br />
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能.<br />
 * 冷饮（Cold drink）.<br />
 * 
 * @author zengtao
 *
 */
public abstract class ColdDrink implements Item {

	/**
	 * 冷饮的打包方式是装瓶
	 */
	public Packing packing() {
		return new Bottle();
	}

	public abstract float price();
}