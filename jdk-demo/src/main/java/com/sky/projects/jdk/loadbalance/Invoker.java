package com.sky.projects.jdk.loadbalance;

public interface Invoker<T> {

	InvokeContext getContext();

	/**
	 * get service interface.
	 * 
	 * @return service interface.
	 */
	Class<T> getInterface();

	/**
	 * invoke.
	 * 
	 * @param invocation
	 * @return result
	 * @throws RpcException
	 */
	Result invoke(Invocation invocation) throws Exception;

}
