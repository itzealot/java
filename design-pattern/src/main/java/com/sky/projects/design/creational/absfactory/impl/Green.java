package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.Color;

/**
 * 含填充绿色方法的类
 * 
 * @author zealot
 *
 */
public class Green implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}
}
