package com.zt.design.creational.prototype;

/**
 * class Square extends Shape.<br />
 * 绘制正方形的类
 * 
 * @author zengtao
 *
 */
public class Square extends Shape {

	public Square() {
		type = "Square";
	}

	/**
	 * draw square
	 */
	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}