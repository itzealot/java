package com.sky.projects.design.gs;

import java.util.LinkedList;

public class RequestQueue<T> {

	// 请求队列
	private LinkedList<T> queue = new LinkedList<>();

	public synchronized T getRequest() {
		while (queue.size() == 0) {// 等待直到有新的 Request
			try {
				System.out.println(Thread.currentThread().getName() + " wait...");
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		// 返回 Request 队列中的第一个请求
		return queue.remove();
	}

	public synchronized void addRequest(T req) {
		queue.add(req);

		// 唤醒等待的请求
		notifyAll();
	}

}
