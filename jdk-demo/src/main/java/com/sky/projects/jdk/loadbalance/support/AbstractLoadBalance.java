package com.sky.projects.jdk.loadbalance.support;

import java.util.List;

import com.sky.projects.jdk.loadbalance.Invocation;
import com.sky.projects.jdk.loadbalance.InvokeContext;
import com.sky.projects.jdk.loadbalance.Invoker;
import com.sky.projects.jdk.loadbalance.LoadBalance;
import com.sky.projects.jdk.loadbalance.LoadBalanceContext;

public abstract class AbstractLoadBalance implements LoadBalance {

	@Override
	public <T> Invoker<T> select(List<Invoker<T>> invokers, LoadBalanceContext context, Invocation invocation)
			throws Exception {
		if (invokers == null || invokers.isEmpty()) {
			return null;
		}

		if (invokers.size() == 1) {
			return invokers.get(0);
		}

		return doSelect(invokers, context, invocation);
	}

	/**
	 * 从目标调用者列表中选择一个调用者返回
	 * 
	 * @param invokers
	 * @param context
	 * @param invocation
	 * @return
	 */
	protected abstract <T> Invoker<T> doSelect(List<Invoker<T>> invokers, LoadBalanceContext context,
			Invocation invocation);

	/**
	 * 计算 Invoker 与请求invocation的权重
	 * 
	 * @param invoker
	 * @param invocation
	 * @return
	 */
	protected <T> int getWeight(Invoker<T> invoker, Invocation invocation) {
		// 方法基本权重值
		InvokeContext context = invoker.getContext();
		int weight = context.getValue(invocation.getMethodName(), 100);

		if (weight > 0) {
			long timestamp = context.getValue("timespan", 0L);

			if (timestamp > 0L) { // 获取调用者中的时间戳
				int uptime = (int) (System.currentTimeMillis() - timestamp); // 与当前系统的时间差值，毫秒数
				int warmup = context.getValue("warmup", 10);
				if (uptime > 0 && uptime < warmup) {
					weight = calculateWarmupWeight(uptime, warmup, weight);
				}
			}
		}

		return weight;
	}

	/**
	 * 根据时间计算热权重，权重值为[1,weight]之间
	 * 
	 * @param uptime
	 * @param warmup
	 * @param weight
	 * @return
	 */
	static int calculateWarmupWeight(int uptime, int warmup, int weight) {
		int ww = (int) ((float) uptime / ((float) warmup / (float) weight));
		return ww < 1 ? 1 : (ww > weight ? weight : ww);
	}
}
