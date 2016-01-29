package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * Square implements Shape.<br />
 * 含画正方形方法的类
 * 
 * @author zengtao
 *
 */
public class Square implements Shape {

	/**
	 * 画正方形的方法
	 */
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}