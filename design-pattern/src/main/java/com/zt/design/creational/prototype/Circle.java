package com.zt.design.creational.prototype;

/**
 * 图形-圆形实现类
 * 
 * @author zengtao
 *
 */
public class Circle extends Shape {

	public Circle() {
		type = "Circle";
	}

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}