package com.sky.projects.design.creational.factory.impl;

import com.sky.projects.design.creational.factory.Shape;

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