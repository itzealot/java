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
	 * 根据颜色名称获取Color引用的对象
	 * 
	 * @param color
	 * @return
	 */
	abstract Color getColor(String color);

	/**
	 * 根据形状获取Shape引用的对象
	 * 
	 * @param shape
	 * @return
	 */
	abstract Shape getShape(String shape);
}
