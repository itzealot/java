package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * Rectangle implements Shape.<br />
 * 含画矩形方法的类
 * 
 * @author zengtao
 *
 */
public class Rectangle implements Shape {

	/**
	 * 画矩形的方法
	 */
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
}
