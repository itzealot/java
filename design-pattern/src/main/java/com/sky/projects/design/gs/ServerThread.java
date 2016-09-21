package com.sky.projects.design.gs;

import com.sky.projects.design.common.Threads;

/**
 * 服务端获取请求队列并进行处理
 * 
 * @author zealot
 */
public class ServerThread implements Runnable {

	// 请求队列
	private RequestQueue<Request> queue;

	public ServerThread(RequestQueue<Request> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			final Request req = queue.getRequest();

			// 模拟处理请求
			Threads.sleep(100);

			System.out.println(Thread.currentThread().getName() + " handle " + req);
		}
	}

}
