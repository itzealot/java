package com.apusic.sicp.service;

import java.io.IOException;
import java.lang.management.MemoryUsage;

import javax.management.remote.JMXConnector;

/**
 * 服务器监控接口
 * 
 * @author zt
 * 
 */
public interface ServerMonitorService {

	/**
	 * 获得JVM堆内存使用信息
	 * 
	 * @param serverCode
	 * @return
	 */
	public MemoryUsage getServerJVMHeapMemoryUsage(String serverCode);

	/**
	 * 获得JVM活动线程数
	 * 
	 * @param serverCode
	 * @return
	 */
	public int getServerJVMThreadCount(String serverCode);

	/**
	 * 获取JMX连接
	 * 
	 * @param host
	 *            the server's host
	 * @param port
	 *            the server's port
	 * @param userName
	 * @param userPwd
	 * @return
	 * @throws IOException
	 */
	public JMXConnector createConnect(String host, String port,
			String userName, String userPwd) throws IOException;
}
