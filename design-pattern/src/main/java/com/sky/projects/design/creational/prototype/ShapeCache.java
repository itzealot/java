package com.sky.projects.design.creational.prototype;

import java.util.Hashtable;

import com.sky.projects.design.creational.prototype.impl.Circle;
import com.sky.projects.design.creational.prototype.impl.Rectangle;
import com.sky.projects.design.creational.prototype.impl.Square;

/**
 * 创建一个类，从数据库获取实体类，并把它们存储在一个 Hashtable 中。
 * 
 * @author zealot
 *
 */
public class ShapeCache {

	private static Hashtable<String, Shape> shapeMap = new Hashtable<>();

	/**
	 * 从HashTabel中根据键获取值
	 * 
	 * @param shapeId
	 * @return
	 */
	public static Shape getShape(String shapeId) {
		Shape cachedShape = shapeMap.get(shapeId);
		// 克隆相应的对象
		return (Shape) cachedShape.clone();
	}

	/**
	 * 对每种形状都运行数据库查询，并创建该形状 shapeMap.put(shapeKey, shape); <br />
	 * 例如，我们要添加三种形状
	 */
	public static void loadCache() {
		Circle circle = new Circle();
		circle.setId("1");
		shapeMap.put(circle.getId(), circle);
		System.out.println("circle:" + circle);

		Square square = new Square();
		square.setId("2");
		shapeMap.put(square.getId(), square);
		System.out.println("square:" + square);

		Rectangle rectangle = new Rectangle();
		rectangle.setId("3");
		shapeMap.put(rectangle.getId(), rectangle);
		System.out.println("rectangle:" + rectangle);
	}
}