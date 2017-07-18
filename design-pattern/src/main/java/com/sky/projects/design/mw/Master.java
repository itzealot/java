package com.sky.projects.design.mw;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Master : 用于任务的分配及结果汇总
 * 
 * @author zealot
 */
public class Master {

	// 任务队列，线程安全
	private Queue<Integer> queue = new ConcurrentLinkedQueue<>();

	// Worker 进程队列
	private Map<String, Thread> threads = new HashMap<>();

	// 子任务处理结果集
	private Map<String, Integer> results = new ConcurrentHashMap<>();

	/**
	 * 
	 * @param worker
	 * @param workerCounts
	 */
	public Master(Worker<Integer> worker, int workerCounts) {
		// 初始化 Worker，共享任务队列与结果集 Map
		worker.setQueue(queue);
		worker.setResults(results);

		// 初始化多个 Worker 线程
		for (int i = 0; i < workerCounts; i++) {
			threads.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
		}
	}

	/**
	 * 任务是否全部完成，当且仅当 Worker 进程全部完成任务
	 * 
	 * @return
	 */
	public boolean isComplete() {
		for (Map.Entry<String, Thread> entry : threads.entrySet()) {
			if (entry.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 提交一个任务，即将任务添加到任务队列(由 Master 负责)
	 * 
	 * @param obj
	 */
	public void submit(Integer obj) {
		queue.add(obj);
	}

	public Map<String, Integer> getResults() {
		return results;
	}

	/**
	 * 开始执行任务，开始运行所有的 Worker 进程
	 */
	public void execute() {
		for (Map.Entry<String, Thread> entry : threads.entrySet()) {
			entry.getValue().start();
		}
	}
}
