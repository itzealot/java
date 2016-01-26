package com.zt.design.structural.decorator;

/**
 * 创建实现了 Shape 接口的抽象装饰类
 * 
 * @author zengtao
 *
 */
public abstract class ShapeDecorator implements Shape {
	// 可用于子类继承
	protected Shape decoratedShape;

	public ShapeDecorator(Shape decoratedShape) {
		this.decoratedShape = decoratedShape;
	}

	public void draw() {
		decoratedShape.draw();
	}
}