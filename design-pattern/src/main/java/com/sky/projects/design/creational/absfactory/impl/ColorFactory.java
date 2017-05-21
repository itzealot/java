package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.AbstractFactory;
import com.sky.projects.design.creational.absfactory.Color;
import com.sky.projects.design.creational.absfactory.Shape;

/**
 * 颜色生产工厂，用于产生不同的颜色对象
 * 
 * @author zealot
 *
 */
public class ColorFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shapeType) {
		return null;
	}

	@Override
	public Color getColor(String color) {
		if (color == null) {
			return null;
		}

		if (color.equalsIgnoreCase("RED")) {
			return new Red();
		} else if (color.equalsIgnoreCase("GREEN")) {
			return new Green();
		} else if (color.equalsIgnoreCase("BLUE")) {
			return new Blue();
		}

		return null;
	}
}