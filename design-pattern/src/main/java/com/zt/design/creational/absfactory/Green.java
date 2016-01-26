package com.zt.design.creational.absfactory;

/**
 * 2. 创建型模式之抽象工厂模式.<br />
 * Green implements Color.<br />
 * 含填充绿色方法的类
 * 
 * @author zengtao
 *
 */
public class Green implements Color {

	/**
	 * 给图形填充绿色
	 */
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}
}
