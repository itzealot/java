package com.sky.projects.design.gs.result;

import com.sky.projects.design.gs.result.impl.RealData;

/**
 * 数据接口
 * 
 * @author zealot
 */
public interface Data {

	/**
	 * 获取结果
	 * 
	 * @return
	 */
	String getResult();

	/**
	 * 设置真实的数据
	 * 
	 * @param data
	 */
	void setRealData(RealData data);
}
