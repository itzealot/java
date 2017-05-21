package com.sky.projects.design.mw;

import java.util.Map;
import java.util.Queue;

/**
 * Worker : 用于处理实际的任务
 * 
 * @author zealot
 */
public abstract class Worker<T> implements Runnable, Handler<T, T> {

	// 任务队列
	private Queue<T> queue;
	// 结果集队列
	private Map<String, T> results;

	public Worker() {
	}

	@Override
	public void run() {
		while (true) {
			T obj = queue.poll();

			if (obj == null) {
				break;
			}

			// 处理任务
			T result = handle(obj);
			System.out.println("finish deal with input : " + obj);

			// 保存结果集
			results.put(Integer.toString(obj.hashCode()), result);
		}
	}

	public abstract T handle(T input);

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
