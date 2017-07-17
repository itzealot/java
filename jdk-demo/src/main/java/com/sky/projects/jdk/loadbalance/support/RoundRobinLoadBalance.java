/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sky.projects.jdk.loadbalance.support;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.sky.projects.jdk.loadbalance.Invocation;
import com.sky.projects.jdk.loadbalance.InvokeContext;
import com.sky.projects.jdk.loadbalance.Invoker;
import com.sky.projects.jdk.loadbalance.LoadBalanceContext;

/**
 * Round robin load balance.轮询负载均衡机制
 *
 * @author qian.lei
 * @author william.liangf
 */
public class RoundRobinLoadBalance extends AbstractLoadBalance {

	public static final String NAME = "roundrobin";
	private final ConcurrentMap<String, AtomicInteger> sequences = new ConcurrentHashMap<>();

	private static final class IntegerWrapper {
		private int value;

		public IntegerWrapper(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void decrement() {
			this.value--;
		}
	}

	@Override
	protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, LoadBalanceContext context, Invocation invocation) {
		InvokeContext ic = invokers.get(0).getContext();
		String key = ic.get("service.key") + "." + invocation.getMethodName();

		int length = invokers.size(); // 总个数
		int maxWeight = 0; // 最大权重
		int minWeight = Integer.MAX_VALUE; // 最小权重
		// 权重 Map 临时记录列表
		final LinkedHashMap<Invoker<T>, IntegerWrapper> invokerToWeightMap = new LinkedHashMap<Invoker<T>, IntegerWrapper>();
		int weightSum = 0; // 权重和
		for (int i = 0; i < length; i++) {
			int weight = getWeight(invokers.get(i), invocation);
			maxWeight = Math.max(maxWeight, weight); // 累计最大权重
			minWeight = Math.min(minWeight, weight); // 累计最小权重

			if (weight > 0) {
				invokerToWeightMap.put(invokers.get(i), new IntegerWrapper(weight));
				weightSum += weight;
			}
		}

		// 根据 key 获取序列号原子计数，不存在则创建
		AtomicInteger sequence = sequences.get(key);
		if (sequence == null) {
			sequences.putIfAbsent(key, new AtomicInteger());
			sequence = sequences.get(key);
		}

		// 序列号累加，达到最大值时重新开始计数
		int currentSequence = sequence.getAndIncrement();
		if (maxWeight > 0 && minWeight < maxWeight) { // 权重不一样
			int mod = currentSequence % weightSum; // 取余权重和

			for (int i = 0; i < maxWeight; i++) { // 遍历所有权重
				for (Map.Entry<Invoker<T>, IntegerWrapper> each : invokerToWeightMap.entrySet()) {
					final Invoker<T> k = each.getKey();
					final IntegerWrapper v = each.getValue();
					if (mod == 0 && v.getValue() > 0) { // 模为0且当前权重>0
						return k;
					}

					if (v.getValue() > 0) { // 权重递减且模递减
						v.decrement();
						mod--;
					}
				}
			}
		}

		// 取模轮循
		return invokers.get(currentSequence % length);
	}

}