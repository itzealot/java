package com.sky.projects.design.structural.facade;

/**
 * 圆形实现类
 * 
 * @author zealot
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}