package com.sky.projects.design.creational.builder;

/**
 * 打包方式是装瓶
 * 
 * @author zealot
 *
 */
public class Bottle implements Packing {

	@Override
	public String pack() {
		// 以瓶子的方式打包
		return "Bottle";
	}
}
