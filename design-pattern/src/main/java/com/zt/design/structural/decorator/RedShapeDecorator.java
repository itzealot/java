package com.zt.design.structural.decorator;

/**
 * 创建扩展了 ShapeDecorator 类的实体装饰类
 * 
 * @author zengtao
 *
 */
public class RedShapeDecorator extends ShapeDecorator {

	/**
	 * 调用父类的构造方法，并为父类构造方法赋值
	 * 
	 * @param decoratedShape
	 */
	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {

		// 调用父类的方法
		decoratedShape.draw();

		// 调用本身方法，即添加的方法用于修饰
		setRedBorder(decoratedShape);
	}

	private void setRedBorder(Shape decoratedShape) {
		System.out.println("Border Color: Red");
	}
}
