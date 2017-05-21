package com.sky.projects.design.gs.task;

import com.sky.projects.design.common.Threads;
import com.sky.projects.design.gs.Request;
import com.sky.projects.design.gs.RequestQueue;

/**
 * 客户端(创建请求并添加到请求队列)
 * 
 * @author zealot
 */
public class ClientThread implements Runnable {

	// 请求队列
	private RequestQueue<Request> queue;

	public ClientThread(RequestQueue<Request> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Request req = new Request("RequestId: " + i + " ThreadName: " + Thread.currentThread().getName());

			System.out.println(Thread.currentThread().getName() + " add request: " + req);

			// 提交请求
			queue.addRequest(req);

			// 客户端请求速度快于服务端处理速度
			Threads.sleep(10);
		}

		System.out.println(Thread.currentThread().getName() + "finish add request.");
	}

}
