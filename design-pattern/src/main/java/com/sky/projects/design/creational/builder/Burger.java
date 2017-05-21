package com.sky.projects.design.creational.builder;

/**
 * 汉堡(Burger)实现 Item 接口的抽象类
 * 
 * @author zealot
 *
 */
public abstract class Burger implements Item {

	@Override
	public Packing packing() {
		// 汉堡的打包方式是用纸包
		return new Wrapper();
	}

	public abstract float price();
}