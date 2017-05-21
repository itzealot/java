package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.Shape;

/**
 * 含画圆方法的类
 * 
 * @author zealot
 *
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}