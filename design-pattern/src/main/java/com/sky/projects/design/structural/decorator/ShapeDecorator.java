package com.sky.projects.design.structural.decorator;

/**
 * 创建实现了 Shape 接口的抽象装饰类
 * 
 * @author zealot
 *
 */
public abstract class ShapeDecorator implements Shape {
	protected Shape decoratedShape;

	public ShapeDecorator(Shape shape) {
		this.decoratedShape = shape;
	}

	@Override
	public void draw() {
		// 调用子类传来的装饰对象进行装饰
		decoratedShape.draw();
	}
}