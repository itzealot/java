package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * 创建扩展了 AbstractFactory 的工厂类.<br />
 * 
 * @author zengtao
 *
 */
public class FactoryProducer {
	/**
	 * 根据不同的工厂信息创建相应的工厂，返回创建后的工厂
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