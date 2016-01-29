package com.zt.design.creational.builder;

/**
 * 实现 Packing 接口的实体类 Bottle。<br />
 * 打包的方式是装在瓶子中。<br />
 * 
 * @author zengtao
 *
 */
public class Bottle implements Packing {

	/**
	 * 以瓶子的方式打包
	 */
	public String pack() {
		return "Bottle";
	}
}
