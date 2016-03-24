package com.projects.sky.zookeeper.listener;

import java.util.List;

/**
 * MonitorCallBack
 * 
 * @author zt
 * @date 2016年1月5日
 */
public interface MonitorCallBack {
	/**
	 * 子节点变化回调
	 * 
	 * @param path
	 * @param node
	 */
	void monitorChildren(String path, List<String> node);

	/**
	 * 数据变化回调
	 * 
	 * @param path
	 * @param data
	 */
	void monitorData(String path, byte[] data);

	/**
	 * 节点变化回调
	 * 
	 * @param path
	 */
	void monitorExists(String path);

}
