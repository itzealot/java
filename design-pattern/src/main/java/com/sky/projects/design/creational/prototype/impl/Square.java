package com.sky.projects.design.creational.prototype.impl;

import com.sky.projects.design.creational.prototype.Shape;

/**
 * 图形-正方形实现类
 * 
 * @author zealot
 *
 */
public class Square extends Shape {

	public Square() {
		type = "Square";
	}

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}