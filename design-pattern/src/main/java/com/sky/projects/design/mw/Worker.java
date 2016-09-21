package com.sky.projects.design.mw;

import java.util.Map;
import java.util.Queue;

/**
 * Worker : 用于处理实际的任务
 * 
 * @author zealot
 */
public class Worker implements Runnable {

	// 任务队列
	private Queue<Object> queue;
	// 结果集队列
	private Map<String, Object> results;

	public Worker() {
	}

	@Override
	public void run() {
		while (true) {
			Object input = queue.poll();

			if (input == null) {
				break;
			}

			// 处理任务
			Object result = handle(input);
			System.out.println("finish deal with input : " + input);

			results.put(Integer.toString(input.hashCode()), result);
		}
	}

	public Object handle(Object input) {
		return new Handler<Object>() {
			@Override
			public Object handle(Object input) {
				return null;
			}
		};
	}

	public Queue<Object> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Object> queue) {
		this.queue = queue;
	}

	public Map<String, Object> getResults() {
		return results;
	}

	public void setResults(Map<String, Object> results) {
		this.results = results;
	}

}
