package com.zt.design.structural.flyweight;

import java.util.HashMap;

/**
 * 创建一个工厂，生成基于给定信息的实体类的对象。
 * 
 * @author zengtao
 *
 */
public class ShapeFactory {
	private static final HashMap<String, Shape> circleMap = new HashMap<String, Shape>();

	/**
	 * 穿件有颜色的圆形对象，有则直接返回；否则创建
	 * 
	 * @param color
	 * @return
	 */
	public static Shape getCircle(String color) {
		// To get object by String color key from HashMap
		Circle circle = (Circle) circleMap.get(color);

		// not find then create and print the first create info
		if (circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);
			System.out.println("Creating circle of color : " + color);
		}

		// has find then return the current
		return circle;
	}
}