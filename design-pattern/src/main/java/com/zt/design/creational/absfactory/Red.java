package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * Red implements Color.<br />
 * 含填充红色方法的类
 * 
 * @author zengtao
 *
 */
public class Red implements Color {

	/**
	 * 给图形填充红色
	 */
	public void fill() {
		System.out.println("Inside Red::fill() method.");
	}
}