package com.sky.projects.jdk.loadbalance;

import java.util.List;

/**
 * 负载均衡调度接口
 * 
 * @author zealot
 */
public interface LoadBalance {

	/**
	 * 从目标调用者列表中选择一个调用者返回
	 * 
	 * @param invokers
	 * @param context
	 * @param invocation
	 * @return
	 * @throws Exception
	 */
	<T> Invoker<T> select(List<Invoker<T>> invokers, LoadBalanceContext context, Invocation invocation)
			throws Exception;

}