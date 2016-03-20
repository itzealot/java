package com.zt.design.creational.builder;

/**
 * 打包方式是装瓶
 * 
 * @author zengtao
 *
 */
public class Bottle implements Packing {

	public String pack() {
		// 以瓶子的方式打包
		return "Bottle";
	}
}
