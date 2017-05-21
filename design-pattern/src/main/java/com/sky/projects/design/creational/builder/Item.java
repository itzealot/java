package com.sky.projects.design.creational.builder;

/**
 * 食物条目和食物包装接口
 * 
 * @author zealot
 *
 */
public interface Item {

	/**
	 * 该项名称
	 * 
	 * @return
	 */
	String name();

	/**
	 * 以何种方式打包
	 * 
	 * @return
	 */
	Packing packing();

	/**
	 * 总价格
	 * 
	 * @return
	 */
	float price();
}
