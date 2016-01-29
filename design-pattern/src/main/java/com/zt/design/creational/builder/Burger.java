package com.zt.design.creational.builder;

/**
 * abstract class Burger implements Item.<br />
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能.<br />
 * 汉堡（Burger）.<br />
 * 
 * @author zengtao
 *
 */
public abstract class Burger implements Item {

	/**
	 * 汉堡的打包方式是用纸包
	 */
	public Packing packing() {
		return new Wrapper();
	}

	/**
	 * 子类需要实现的价格
	 */
	public abstract float price();
}