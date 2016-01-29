package com.zt.design.structural.bridge;

/**
 * 实现了 DrawAPI 接口的实体桥接实现类 RedCircle
 * 
 * @author zengtao
 *
 */
public class RedCircle implements DrawAPI {
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: red, radius: " + radius
				+ ", x: " + x + ", " + y + "]");
	}
}