package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * ShapeFactory extends AbstractFactory.<br />
 * 图形生产工厂，用于生产不同的图形对象
 * 
 * @author zengtao
 *
 */
public class ShapeFactory extends AbstractFactory {

	/**
	 * 根据String类型的shape实例来生产实现了Shape接口的对象
	 */
	@Override
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}

	@Override
	Color getColor(String color) {
		return null;
	}
}