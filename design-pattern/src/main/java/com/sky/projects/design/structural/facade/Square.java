package com.sky.projects.design.structural.facade;

/**
 * 正方形实现类
 * 
 * @author zt
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}