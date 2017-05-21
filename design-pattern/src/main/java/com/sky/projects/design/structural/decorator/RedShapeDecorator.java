package com.sky.projects.design.structural.decorator;

/**
 * 创建扩展了抽象装饰类的实体装饰类
 * 
 * @author zealot
 */
public class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape shape) {
		super(shape);
	}

	@Override
	public void draw() {
		decoratedShape.draw();

		// 修饰要装饰的对象
		setRedBorder(decoratedShape);
	}

	/**
	 * 装饰需要装饰的对象
	 * 
	 * @param decoratedShape
	 */
	private void setRedBorder(Shape decoratedShape) {
		System.out.println("Border Color: Red");
	}
}
