package com.sky.projects.design.creational.absfactory.impl;

import com.sky.projects.design.creational.absfactory.Color;

/**
 * 填充红色的实现类
 * 
 * @author zealot
 *
 */
public class Red implements Color {

	@Override
	public void fill() {
		// 给图形填充红色
		System.out.println("Inside Red::fill() method.");
	}
}