package com.zt.design.creational.builder;

/**
 * 打包方式的接口
 * 
 * @author zengtao
 *
 */
public interface Packing {

	/**
	 * 实现打包的函数，返回打包的方式，以字符串表示
	 * 
	 * @return the packing way
	 */
	public String pack();
}
