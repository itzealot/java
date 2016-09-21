package com.sky.projects.design.gs.result;

import com.sky.projects.design.future.demo.Data;
import com.sky.projects.design.future.demo.RealData;
import com.sky.projects.design.gs.RequestQueue;

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

			final Data future = req.getResponse();

			// RealData 创建比较耗时
			RealData data = new RealData(req.getName());
			// 处理完成后设置给客户进程
			future.setRealData(data);

			System.out.println(Thread.currentThread().getName() + " handle " + req);
		}
	}

}
