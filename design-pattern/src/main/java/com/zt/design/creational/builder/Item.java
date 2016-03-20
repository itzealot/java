package com.zt.design.creational.builder;

/**
 * 食物条目和食物包装接口
 * 
 * @author zengtao
 *
 */
public interface Item {

	/**
	 * 该项名称
	 * 
	 * @return
	 */
	public String name();

	/**
	 * 以何种方式打包
	 * 
	 * @return
	 */
	public Packing packing();

	/**
	 * 总价格
	 * 
	 * @return
	 */
	public float price();
}
