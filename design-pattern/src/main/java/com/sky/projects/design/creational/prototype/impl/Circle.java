package com.sky.projects.design.creational.prototype.impl;

import com.sky.projects.design.creational.prototype.Shape;

/**
 * 图形-圆形实现类
 * 
 * @author zealot
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