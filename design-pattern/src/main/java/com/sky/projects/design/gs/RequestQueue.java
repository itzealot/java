package com.sky.projects.design.gs;

import java.util.LinkedList;

import com.sky.projects.design.common.Threads;

/**
 * 请求队列(共享数据)
 * 
 * @author zealot
 */
public class RequestQueue<T> implements Queue<T> {

	// 请求队列
	private LinkedList<T> queue = new LinkedList<>();

	@Override
	public synchronized T getRequest() {
		while (queue.size() == 0) {// 等待直到有新的 Request
			System.out.println(Thread.currentThread().getName() + " wait...");
			Threads.wait(this);
		}

		// 返回 Request 队列中的第一个请求
		return queue.remove();
	}

	@Override
	public synchronized void addRequest(T req) {
		queue.add(req);
		this.notifyAll(); // 唤醒等待获取请求的线程(getRequest)
	}

}
