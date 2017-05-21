package com.sky.projects.design.creational.prototype.impl;

import com.sky.projects.design.creational.prototype.Shape;

/**
 * 图形-矩形实现类
 * 
 * @author zealot
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