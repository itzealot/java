package com.sky.projects.design.structural.decorator;

/**
 * 绘制圆形实现类
 * 
 * @author zealot
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Circle");
	}
}
