package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * Blue implements Color.<br />
 * 含填充蓝色方法的类
 * 
 * @author zengtao
 *
 */
public class Blue implements Color {

	/**
	 * 给图形填充蓝色
	 */
	public void fill() {
		System.out.println("Inside Blue::fill() method.");
	}
}
