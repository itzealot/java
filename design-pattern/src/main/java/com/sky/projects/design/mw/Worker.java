package com.sky.projects.design.mw;

import java.util.Map;
import java.util.Queue;

/**
 * Worker : 用于处理实际的任务
 * 
 * @author zealot
 */
public abstract class Worker<T> implements Runnable {

	private Queue<T> queue; // 任务队列
	private Map<String, T> results; // 结果集队列

	public Worker() {
	}

	@Override
	public void run() {
		while (true) {
			T obj = queue.poll();

			if (obj == null) {
				break;
			}

			T result = handle(obj); // 处理任务
			System.out.println("finish deal with input : " + obj);

			// 保存结果集
			results.put(Integer.toString(obj.hashCode()), result);
		}
	}

	/**
	 * 处理任务并返回结果
	 * 
	 * @param input
	 * @return
	 */
	protected abstract T handle(T input);

	public Queue<T> getQueue() {
		return queue;
	}

	public void setQueue(Queue<T> queue) {
		this.queue = queue;
	}

	public Map<String, T> getResults() {
		return results;
	}

	public void setResults(Map<String, T> results) {
		this.results = results;
	}

}
