package com.zt.design.structural.flyweight;

import java.util.HashMap;

/**
 * 工厂类：图像工厂类，生成基于给定信息的实体类的对象
 * 
 * @author zengtao
 *
 */
public class ShapeFactory {

	// Shape 缓存 Map
	private static final HashMap<String, Shape> circleMap = new HashMap<String, Shape>();

	/**
	 * 根据颜色获取圆形对象，有则直接返回；否则创建
	 * 
	 * @param color
	 * @return
	 */
	public static Shape getCircle(String color) {
		Circle circle = (Circle) circleMap.get(color);

		if (circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);

			System.out.println("Creating circle of color : " + color);
		}

		return circle;
	}
}