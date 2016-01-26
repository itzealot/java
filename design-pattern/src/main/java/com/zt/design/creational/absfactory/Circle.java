package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * Circle implements Shape.<br />
 * 含画圆方法的类
 * 
 * @author zengtao
 *
 */
public class Circle implements Shape {

	/**
	 * 画圆的方法
	 */
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}