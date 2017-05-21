package com.sky.projects.design.structural.decorator;

/**
 * 绘制矩形实现类
 * 
 * @author zealot
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Rectangle");
	}
}