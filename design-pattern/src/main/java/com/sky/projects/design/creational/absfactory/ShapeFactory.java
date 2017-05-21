package com.sky.projects.design.creational.absfactory;

import com.sky.projects.design.creational.absfactory.impl.Circle;
import com.sky.projects.design.creational.absfactory.impl.Rectangle;
import com.sky.projects.design.creational.absfactory.impl.Square;

/**
 * 图形生产工厂，用于生产不同的图形对象
 * 
 * @author zealot
 *
 */
public class ShapeFactory extends AbstractFactory {

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
	public Color getColor(String color) {
		return null;
	}
}