/*
 * Copyright 1999-2012 Alibaba Group.
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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sky.projects.jdk.loadbalance.Invocation;
import com.sky.projects.jdk.loadbalance.InvokeContext;
import com.sky.projects.jdk.loadbalance.Invoker;
import com.sky.projects.jdk.loadbalance.LoadBalanceContext;

/**
 * ConsistentHashLoadBalance，一致性hash负载均衡
 * {@link http://blog.csdn.net/cywosp/article/details/23397179}
 * {@link http://www.cnblogs.com/xrq730/p/5186728.html}
 * 
 * @author william.liangf
 */
public class ConsistentHashLoadBalance extends AbstractLoadBalance {

	// 一致性hash存储
	private final ConcurrentMap<String, ConsistentHashSelector<?>> selectors = new ConcurrentHashMap<String, ConsistentHashSelector<?>>();

	@SuppressWarnings("unchecked")
	@Override
	protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, LoadBalanceContext context, Invocation invocation) {
		// 调用信息Key(类名称+方法名称)
		InvokeContext ic = invokers.get(0).getContext();
		String key = ic.get("service.key") + "." + invocation.getMethodName();
		int identityHashCode = System.identityHashCode(invokers);
		ConsistentHashSelector<T> selector = (ConsistentHashSelector<T>) selectors.get(key);

		// 没有获取或hashCode 不一致，则新增而后再获取
		if (selector == null || selector.getIdentityHashCode() != identityHashCode) {
			selectors.put(key, new ConsistentHashSelector<T>(invokers, invocation.getMethodName(), identityHashCode));
			selector = (ConsistentHashSelector<T>) selectors.get(key);
		}

		return selector.select(invocation);
	}

	private static final class ConsistentHashSelector<T> {
		// 虚拟节点,key=hash,value=Invoker
		private final TreeMap<Long, Invoker<T>> virtualInvokers;
		private final int replicaNumber; // 副本数
		private final int identityHashCode; // hashCode
		private final int[] argumentIndex; // 参数

		public ConsistentHashSelector(List<Invoker<T>> invokers, String methodName, int identityHashCode) {
			this.virtualInvokers = new TreeMap<Long, Invoker<T>>();
			this.identityHashCode = System.identityHashCode(invokers);

			InvokeContext ic = invokers.get(0).getContext();

			this.replicaNumber = ic.getMethodParameter(methodName, "hash.nodes", 160);
			String[] index = Constants.COMMA_SPLIT_PATTERN
					.split(ic.getMethodParameter(methodName, "hash.arguments", "0"));
			argumentIndex = new int[index.length];

			for (int i = 0; i < index.length; i++) {
				argumentIndex[i] = Integer.parseInt(index[i]);
			}

			// 初始化虚拟节点数
			for (Invoker<T> invoker : invokers) {
				for (int i = 0; i < replicaNumber / 4; i++) {
					// url+编号
					byte[] digest = md5(ic.get("service.key") + i);
					for (int h = 0; h < 4; h++) {
						long m = hash(digest, h);
						virtualInvokers.put(m, invoker);
					}
				}
			}
		}

		public int getIdentityHashCode() {
			return identityHashCode;
		}

		public Invoker<T> select(Invocation invocation) {
			String key = toKey(invocation.getArguments());
			byte[] digest = md5(key);
			Invoker<T> invoker = sekectForKey(hash(digest, 0));
			return invoker;
		}

		private String toKey(Object[] args) {
			StringBuilder buf = new StringBuilder();
			for (int i : argumentIndex) {
				if (i >= 0 && i < args.length) {
					buf.append(args[i]);
				}
			}
			return buf.toString();
		}

		private Invoker<T> sekectForKey(long hash) {
			Invoker<T> invoker;
			Long key = hash;
			if (!virtualInvokers.containsKey(key)) {
				SortedMap<Long, Invoker<T>> tailMap = virtualInvokers.tailMap(key);
				if (tailMap.isEmpty()) {
					key = virtualInvokers.firstKey();
				} else {
					key = tailMap.firstKey();
				}
			}
			invoker = virtualInvokers.get(key);
			return invoker;
		}

		private long hash(byte[] digest, int number) {
			return (((long) (digest[3 + number * 4] & 0xFF) << 24) | ((long) (digest[2 + number * 4] & 0xFF) << 16)
					| ((long) (digest[1 + number * 4] & 0xFF) << 8) | (digest[0 + number * 4] & 0xFF)) & 0xFFFFFFFFL;
		}

		private byte[] md5(String value) {
			MessageDigest md5;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
			md5.reset();
			byte[] bytes = null;
			try {
				bytes = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
			md5.update(bytes);
			return md5.digest();
		}

	}

}
