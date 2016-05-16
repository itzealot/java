package com.sky.projects.servlet.proxy.http;

/**
 * Http客户端处理器，用于处理远程调用的接口
 * 
 * @author zt
 *
 */
public interface HttpClientHandler {
	/**
	 * 根据服务信息进行远程调用并返回结果
	 */
	public String doHandler();
}
