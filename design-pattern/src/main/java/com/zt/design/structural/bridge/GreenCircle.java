package com.zt.design.structural.bridge;

/**
 * 实现了 DrawAPI 接口的实体桥接实现类 GreenCircle
 * 
 * @author zengtao
 *
 */
public class GreenCircle implements DrawAPI {
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: green, radius: " + radius
				+ ", x: " + x + ", " + y + "]");
	}
}