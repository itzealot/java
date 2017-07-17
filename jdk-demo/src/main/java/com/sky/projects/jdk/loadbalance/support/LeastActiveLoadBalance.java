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

import java.util.List;
import java.util.Random;

import com.sky.projects.jdk.loadbalance.Invocation;
import com.sky.projects.jdk.loadbalance.InvokeContext;
import com.sky.projects.jdk.loadbalance.Invoker;
import com.sky.projects.jdk.loadbalance.LoadBalanceContext;
import com.sky.projects.jdk.loadbalance.RpcStatus;

/**
 * LeastActiveLoadBalance，最少活跃数负载均衡
 * 
 * @author william.liangf
 */
public class LeastActiveLoadBalance extends AbstractLoadBalance {

	public static final String NAME = "leastactive";
	private final Random random = new Random();

	@Override
	protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, LoadBalanceContext context, Invocation invocation) {
		int length = invokers.size(); // 总个数
		int leastActive = -1; // 最小的活跃数
		int leastCount = 0; // 相同最小活跃数的个数
		int[] leastIndexs = new int[length]; // 相同最小活跃数的下标
		int totalWeight = 0; // 总权重
		int firstWeight = 0; // 第一个权重，用于于计算是否相同
		boolean sameWeight = true; // 是否所有权重相同

		for (int i = 0; i < length; i++) {
			Invoker<T> invoker = invokers.get(i);
			// 根据 Rpc 统计信息找出最小活跃计数
			InvokeContext ic = invoker.getContext();
			int active = RpcStatus.getStatus(ic, invocation.getMethodName()).getActive(); // 活跃数
			int weight = ic.getValue(invocation.getMethodName(), 100); // 权重

			if (leastActive == -1 || active < leastActive) { // 发现更小的活跃数，重新开始
				leastActive = active; // 记录最小活跃数
				leastCount = 1; // 重新统计相同最小活跃数的个数
				leastIndexs[0] = i; // 重新记录最小活跃数下标
				totalWeight = weight; // 重新累计总权重
				firstWeight = weight; // 记录第一个权重
				sameWeight = true; // 还原权重相同标识
			} else if (active == leastActive) { // 累计相同最小的活跃数
				leastIndexs[leastCount++] = i; // 累计相同最小活跃数下标
				totalWeight += weight; // 累计总权重

				// 判断所有权重是否一样
				if (sameWeight && i > 0 && weight != firstWeight) {
					sameWeight = false;
				}
			}
		}

		// assert(leastCount > 0)
		if (leastCount == 1) {
			// 如果只有一个最小则直接返回
			return invokers.get(leastIndexs[0]);
		}

		// 如果权重不相同且权重大于0则按总权重数随机
		if (!sameWeight && totalWeight > 0) {
			int offsetWeight = random.nextInt(totalWeight);

			// 并确定随机值落在哪个片断上
			for (int i = 0; i < leastCount; i++) {
				int leastIndex = leastIndexs[i];
				offsetWeight -= getWeight(invokers.get(leastIndex), invocation);
				if (offsetWeight <= 0)
					return invokers.get(leastIndex);
			}
		}

		// 如果权重相同或权重为0则均等随机
		return invokers.get(leastIndexs[random.nextInt(leastCount)]);
	}
}