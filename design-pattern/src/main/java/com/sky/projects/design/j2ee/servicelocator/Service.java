package com.sky.projects.design.j2ee.servicelocator;

/**
 * 服务接口
 * 
 * @author zealot
 *
 */
public interface Service {
	/**
	 * 获取名称
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 执行
	 */
	void execute();
}