package com.zt.design.creational.builder;

/**
 * 汉堡（Burger）实现 Item 接口的抽象类
 * 
 * @author zengtao
 *
 */
public abstract class Burger implements Item {

	public Packing packing() {
		// 汉堡的打包方式是用纸包
		return new Wrapper();
	}

	public abstract float price();
}