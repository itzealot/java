package com.zt.design.creational.builder;

/**
 * 打包方式的接口 Packing
 * 
 * @author zengtao
 *
 */
public interface Packing {
	/**
	 * 实现打包的函数。<br />
	 * 返回打包的方式，以字符串表示。<br />
	 * 
	 * @return the packing way
	 */
	public String pack();
}
