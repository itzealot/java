package com.zt.design.creational.absfactory;

/**
 * 填充红色的实现类
 * 
 * @author zengtao
 *
 */
public class Red implements Color {

	public void fill() {
		// 给图形填充红色
		System.out.println("Inside Red::fill() method.");
	}
}