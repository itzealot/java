package com.zt.design.creational.absfactory;

/**
 * 颜色-蓝色实现类
 * 
 * @author zengtao
 *
 */
public class Blue implements Color {

	public void fill() {
		// 给图形填充蓝色
		System.out.println("Inside Blue::fill() method.");
	}
}
