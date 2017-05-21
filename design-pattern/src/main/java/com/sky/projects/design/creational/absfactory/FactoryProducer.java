package com.sky.projects.design.creational.absfactory;

import com.sky.projects.design.creational.absfactory.impl.ColorFactory;

/**
 * 创建扩展了 AbstractFactory 的工厂类
 * 
 * @author zealot
 *
 */
public class FactoryProducer {

	/**
	 * 根据名称创建工厂，并返回
	 * 
	 * @param choice
	 * @return
	 */
	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("SHAPE")) {
			return new ShapeFactory();
		} else if (choice.equalsIgnoreCase("COLOR")) {
			return new ColorFactory();
		}
		return null;
	}
}