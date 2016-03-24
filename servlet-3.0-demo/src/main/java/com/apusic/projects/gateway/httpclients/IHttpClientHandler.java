package com.apusic.projects.gateway.httpclients;

/**
 * interface IHttpClientHandler : Http Client Handler. <br />
 * Http客户端处理器，用于处理远程调用的接口
 * 
 * @author a
 *
 */
public interface IHttpClientHandler {
	/**
	 * 根据服务信息进行远程调用并返回结果
	 */
	public String doHandler();
}
