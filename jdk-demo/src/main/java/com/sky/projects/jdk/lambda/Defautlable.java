package com.sky.projects.jdk.lambda;

/**
 * 
 * @author zealot
 *
 */
public interface Defautlable {

	/**
	 * 接口运行默认的方法，实现类可以选择不覆盖或覆盖该方法
	 */
	default String notRequired() {
		return "Default implementation";
	}
}