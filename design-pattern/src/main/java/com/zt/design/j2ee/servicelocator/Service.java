package com.zt.design.j2ee.servicelocator;

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
	public String getName();

	/**
	 * 执行
	 */
	public void execute();
}