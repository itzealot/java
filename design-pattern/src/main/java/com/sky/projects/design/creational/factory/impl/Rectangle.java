package com.sky.projects.design.creational.factory.impl;

import com.sky.projects.design.creational.factory.Shape;

/**
 * 图形-矩形实现类
 * 
 * @author zealot
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		// 画矩形的方法
		System.out.println("Inside Rectangle::draw() method.");
	}
}
