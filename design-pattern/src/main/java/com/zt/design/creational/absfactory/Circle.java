package com.zt.design.creational.absfactory;

/**
 * 含画圆方法的类
 * 
 * @author zengtao
 *
 */
public class Circle implements Shape {

	/**
	 * 画圆的方法
	 */
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}