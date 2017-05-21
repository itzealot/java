package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.Shape;

/**
 * 图形-正方形实现类
 * 
 * @author zealot
 *
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}