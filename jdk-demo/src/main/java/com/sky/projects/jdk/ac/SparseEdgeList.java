/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sky.projects.jdk.ac;

/**
 * Linked list implementation of the EdgeList should be less memory-intensive.
 * 
 * jon: tweaked to be type generic
 * 
 * 稀疏边缘列表
 */
@SuppressWarnings("serial")
class SparseEdgeList<T> implements EdgeList<T> {
	/** 链表头指针 */
	private Cons<T> head;

	public SparseEdgeList() {
		head = null;
	}

	@Override
	public State<T> get(byte b) {
		Cons<T> c = head;
		while (c != null) {
			if (c.b == b)
				return c.s;
			c = c.next;
		}
		return null;
	}

	@Override
	public void put(byte b, State<T> s) {
		// 链表采用头插法，原来的头指针指向的链表变为后继，而现在的头指针指向新增的链表节点
		this.head = new Cons<T>(b, s, head);
	}

	@Override
	public byte[] keys() {
		int length = 0;
		// 指向头指针
		Cons<T> c = head;

		// 计算链表的长度
		while (c != null) {
			length++;
			c = c.next;
		}

		byte[] result = new byte[length];
		c = head;
		int j = 0;
		// 遍历列表结构
		while (c != null) {
			result[j] = c.b;
			j++;
			c = c.next;
		}
		return result;
	}

	/**
	 * 链表节点数据结构(即节点的子节点存储数据结构)
	 */
	static private class Cons<T> {
		/** 当前字节 */
		byte b;
		/** 节点指针 */
		State<T> s;
		/** 链表后继 */
		Cons<T> next;

		public Cons(byte b, State<T> s, Cons<T> next) {
			this.b = b;
			this.s = s;
			this.next = next;
		}
	}

	@Override
	public void clear() {
		head = null;
	}

}
