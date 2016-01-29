package com.zt.design.creational.factory;

/**
 * 1. 创建型模式之工厂模式.<br />
 * Rectangle implements Shape.<br />
 * 实现画矩形的方法
 * 
 * @author zengtao
 *
 */
public class Rectangle implements Shape {

	/**
	 * 画矩形的方法
	 */
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
}
