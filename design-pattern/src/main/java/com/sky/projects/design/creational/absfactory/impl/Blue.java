package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.Color;

/**
 * 颜色-蓝色实现类
 * 
 * @author zealot
 */
public class Blue implements Color {

	@Override
	public void fill() {
		// 给图形填充蓝色
		System.out.println("Inside Blue::fill() method.");
	}
}
