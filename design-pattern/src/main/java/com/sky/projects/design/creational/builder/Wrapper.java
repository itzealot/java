package com.sky.projects.design.creational.builder;

/**
 * 打包方式是包在纸盒中
 * 
 * @author zealot
 *
 */
public class Wrapper implements Packing {

	@Override
	public String pack() {
		// 以纸盒的方式打包
		return "Wrapper";
	}
}