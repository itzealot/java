package com.sky.projects.design.behavior.chain.filter;

public interface Invoker {

	/**
	 * 1.执行请求的调用，根据请求对象相关的信息(Class 对象，方法名称，方法参数类型，参数可以确定需要调用的服务).<br>
	 * 2.通过反射的方式获取服务并执行调用，并封装成响应对象返回
	 * 
	 * @param req
	 * @return
	 */
	Response invoke(Request req);

}
