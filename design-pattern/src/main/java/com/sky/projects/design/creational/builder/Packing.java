package com.sky.projects.design.creational.builder;

/**
 * 打包方式的接口
 * 
 * @author zealot
 *
 */
public interface Packing {

	/**
	 * 实现打包的函数，返回打包的方式，以字符串表示
	 * 
	 * @return the packing way
	 */
	String pack();
}
