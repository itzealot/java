package com.sky.projects.util;

import java.util.List;

/**
 * 信息接收接口
 * 
 * @author zealot
 *
 * @param <T>
 */
public interface SkyAcceptable<T> {

	/**
	 * 接收单个信息
	 * 
	 * @param message
	 */
	public void accept(T message);

	/**
	 * 接收多个信息
	 * 
	 * @param message
	 */
	public void accept(List<T> messages);
}
