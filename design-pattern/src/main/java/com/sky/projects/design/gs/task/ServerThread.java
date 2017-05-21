package com.sky.projects.design.gs.task;

import com.sky.projects.design.common.Threads;
import com.sky.projects.design.gs.Request;
import com.sky.projects.design.gs.RequestHandler;
import com.sky.projects.design.gs.RequestQueue;

/**
 * 服务端(从请求队列获取请求并进行处理)
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

			System.out.println(Thread.currentThread().getName() + " finish handle: " + new RequestHandler() {
				@Override
				public String handle(Request req) {
					// 返回出境结果
					return req.toString();
				}
			}.handle(req));
		}
	}

}
