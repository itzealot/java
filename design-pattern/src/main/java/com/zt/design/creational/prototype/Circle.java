package com.zt.design.creational.prototype;

/**
 * class Circle extends Shape.<br />
 * 绘制圆形的类
 * 
 * @author zengtao
 *
 */
public class Circle extends Shape {

	public Circle() {
		type = "Circle";
	}

	/**
	 * draw circle
	 */
	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}