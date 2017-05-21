package com.sky.projects.design.structural.bridge;

/**
 * 使用 DrawAPI 接口创建抽象类 Shape
 * 
 * @author zealot
 *
 */
public abstract class Shape {
	protected DrawAPI drawAPI;

	protected Shape(DrawAPI drawAPI) {
		this.drawAPI = drawAPI;
	}

	public abstract void draw();
}