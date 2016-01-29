package com.zt.design.creational.factory;

/**
 * 1. 创建型模式之工厂模式.<br />
 * Circle implements Shape.<br />
 * 实现画圆的方法
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