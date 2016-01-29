package com.zt.design.creational.prototype;

/**
 * class Rectangle extends Shape.<br />
 * 绘制矩形的类
 * 
 * @author zengtao
 *
 */
public class Rectangle extends Shape {

	public Rectangle() {
		type = "Rectangle";
	}

	/**
	 * draw rectangle
	 */
	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
}