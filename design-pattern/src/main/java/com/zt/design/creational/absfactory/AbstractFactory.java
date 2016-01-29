package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * 为 Color 和 Shape 对象创建抽象类来获取工厂
 * 
 * @author zengtao
 *
 */
public abstract class AbstractFactory {
	/**
	 * 根据String类型的color实例来生产实现了Color接口的对象
	 * 
	 * @param color
	 * @return
	 */
	abstract Color getColor(String color);

	/**
	 * 根据String类型的shape实例来生产实现了Shape接口的对象
	 * 
	 * @param shape
	 * @return
	 */
	abstract Shape getShape(String shape);
}
