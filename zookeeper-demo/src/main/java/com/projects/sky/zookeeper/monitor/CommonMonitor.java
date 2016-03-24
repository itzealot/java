package com.projects.sky.zookeeper.monitor;

import com.projects.sky.zookeeper.listener.MonitorCallBack;

public interface CommonMonitor {
	/**
	 * 创建子节点变化监听器
	 * 
	 * @param callBack
	 * @param path
	 */
	void createChilrendMoniotr(MonitorCallBack callBack, String... path);

	/**
	 * 创建数据更新监听器
	 * 
	 * @param callBack
	 * @param path
	 */
	void createDataMonitor(MonitorCallBack callBack, String... path);

	/**
	 * 创建节点监控监听器
	 * 
	 * @param callBack
	 * @param path
	 */
	void createExistsMonitor(MonitorCallBack callBack, String... path);

}
