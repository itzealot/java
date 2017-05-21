package com.sky.projects.design.structural.flyweight;

/**
 * 圆形实现类
 * 
 * @author zealot
 */
public class Circle implements Shape {

	// 颜色
	private String color;

	// 坐标 x
	private int x;

	// 坐标 y
	private int y;

	// 弧度
	private int radius;

	public Circle(String color) {
		this.color = color;
	}

	@Override
	public void draw() {
		System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}