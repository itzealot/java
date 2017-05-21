package com.sky.projects.design.creational.factory.impl;

import com.sky.projects.design.creational.factory.Shape;

/**
 * 图形-圆形实现类
 * 
 * @author zealot
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		// 画圆的方法
		System.out.println("Inside Circle::draw() method.");
	}
}