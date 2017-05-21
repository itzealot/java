package com.sky.projects.design.structural.facade;

public class ShapeMaker {
	// 持有构建圆形的实例
	private Shape circle;

	// 持有构建矩形的实例
	private Shape rectangle;

	// 持有构建正方形的实例
	private Shape square;

	public ShapeMaker() {
		init();
	}

	/**
	 * 初始化各种构建
	 */
	private void init() {
		circle = new Circle();
		rectangle = new Rectangle();
		square = new Square();
	}

	/**
	 * 绘制圆形
	 */
	public void drawCircle() {
		circle.draw();
	}

	/**
	 * 绘制矩形
	 */
	public void drawRectangle() {
		rectangle.draw();
	}

	/**
	 * 绘制正方形
	 */
	public void drawSquare() {
		square.draw();
	}
}
