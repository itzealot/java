package com.sky.projects.design.gs.result.task;

import java.util.ArrayList;
import java.util.List;

import com.sky.projects.design.common.Threads;
import com.sky.projects.design.gs.RequestQueue;
import com.sky.projects.design.gs.result.Request;
import com.sky.projects.design.gs.result.impl.FutureData;

/**
 * 客户端(发起请求并放入请求队列)
 * 
 * @author zealot
 */
public class ClientThread implements Runnable {

	// 请求队列
	private RequestQueue<Request> queue;
	private List<Request> requests = new ArrayList<>();

	public ClientThread(RequestQueue<Request> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Request req = new Request("RequestId: " + i);

			System.out.println(Thread.currentThread().getName() + " requests " + req);

			req.setResponse(new FutureData());

			// 提交请求
			queue.addRequest(req);

			// 发送请求
			requests.add(req);

			// 客户端请求速度快于服务端处理速度
			Threads.sleep(10);
		}

		System.out.println(Thread.currentThread().getName() + " request end.");

		for (Request r : requests) {
			// 服务器未处理完，会等待
			System.out.println("[ClientThread Name is : " + Thread.currentThread().getName() + "], [Response is : "
					+ r.getResponse().getResult() + "]");
		}

	}

}
