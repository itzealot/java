package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.Shape;

/**
 * 图形-矩形实现类
 * 
 * @author zealot
 *
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
}
