package com.sky.projects.design.structural.facade;

/**
 * 矩形实现类
 * 
 * @author zealot
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
}
