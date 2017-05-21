package com.sky.projects.design.structural.bridge;

/**
 * 桥接实现接口
 * 
 * @author zealot
 *
 */
public interface DrawAPI {

	/**
	 * 绘制圆
	 * 
	 * @param radius
	 * @param x
	 * @param y
	 */
	void drawCircle(int radius, int x, int y);
}