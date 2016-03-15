package com.zt.design.structural.decorator;

/**
 * 创建实现了 Shape 接口的抽象装饰类
 * 
 * @author zengtao
 *
 */
public abstract class ShapeDecorator implements Shape {
	protected Shape decoratedShape;

	public ShapeDecorator(Shape decoratedShape) {
		this.decoratedShape = decoratedShape;
	}

	public void draw() {
		// 调用子类传来的装饰对象进行装饰
		decoratedShape.draw();
	}
}