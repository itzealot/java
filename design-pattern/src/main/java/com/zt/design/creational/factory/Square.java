package com.zt.design.creational.factory;

/**
 * 1. 创建型模式之工厂模式.<br />
 * Square implements Shape.<br />
 * 实现画正方形的方法
 * 
 * @author zengtao
 *
 */
public class Square implements Shape {

	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}