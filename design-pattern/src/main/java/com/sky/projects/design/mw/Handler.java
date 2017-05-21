package com.sky.projects.design.mw;

/**
 * 处理接口
 * 
 * @author zealot
 */
public interface Handler<T1, T2> {

	/**
	 * 处理数据并返回结果
	 * 
	 * @param input
	 * @return
	 */
	T2 handle(T1 input);
}
