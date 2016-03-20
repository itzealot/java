package com.zt.design.creational.prototype;

/**
 * 图形-矩形实现类
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