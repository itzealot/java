package com.zt.design.creational.prototype;

/**
 * 图形-正方形实现类
 * 
 * @author zengtao
 *
 */
public class Square extends Shape {

	public Square() {
		type = "Square";
	}

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}