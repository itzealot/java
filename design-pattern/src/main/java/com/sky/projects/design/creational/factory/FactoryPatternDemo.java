package com.sky.projects.design.creational.factory;

/**
 * 1. 创建型模式之工厂模式.<br />
 * 测试类FactoryPatternDemo --测试工厂模式
 * 
 * @author zealot
 *
 */
public class FactoryPatternDemo {

	public static void main(String[] args) {
		ShapeFactory factory = new ShapeFactory();

		// 获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = factory.getShape("circle");

		// 调用 Circle 的 draw 方法
		shape1.draw();

		// 获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = factory.getShape("RECTANGLE");

		// 调用 Rectangle 的 draw 方法
		shape2.draw();

		// 获取 Square 的对象，并调用它的 draw 方法
		Shape shape3 = factory.getShape("SQUARE");

		// 调用 Square 的 draw 方法
		shape3.draw();
	}
}