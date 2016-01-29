package com.zt.design.creational.builder;

/**
 * 实现 Packing 接口的实体类。 <br />
 * 打包的方式是包在纸盒中。<br />
 * 
 * @author zengtao
 *
 */
public class Wrapper implements Packing {

	/**
	 * 以纸盒的方式打包
	 */
	public String pack() {
		return "Wrapper";
	}
}