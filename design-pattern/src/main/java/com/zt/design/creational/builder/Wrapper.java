package com.zt.design.creational.builder;

/**
 * 打包方式是包在纸盒中
 * 
 * @author zengtao
 *
 */
public class Wrapper implements Packing {

	public String pack() {
		// 以纸盒的方式打包
		return "Wrapper";
	}
}