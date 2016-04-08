package com.sky.projects.jdk.callback;

/**
 * 
 * 回调接口类
 * 
 * @author zt
 *
 */
public interface CSCallBack {
	/**
	 * 回调函数，返回状态
	 * 
	 * @param status
	 *            状态
	 */
	public void process(String status);
}