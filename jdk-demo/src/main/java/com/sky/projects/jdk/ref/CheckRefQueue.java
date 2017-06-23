package com.sky.projects.jdk.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * CheckRefQueue
 * 
 * @author zealot
 *
 * @param <T>
 */
public class CheckRefQueue<T> implements Runnable {

	private ReferenceQueue<T> queue;
	private boolean running;

	public CheckRefQueue(ReferenceQueue<T> queue) {
		this.queue = queue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (running) {
			try {
				// 从引用队列中移除，阻塞方法，被回收时才会调用，否则阻塞
				Reference<T> obj = (Reference<T>) queue.remove();

				if (obj != null) {
					System.out.println("Object for Reference:" + obj.get());
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void shutdown() {
		this.running = false;
	}
}
