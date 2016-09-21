package com.sky.projects.design.gs.result;

import com.sky.projects.design.gs.RequestQueue;

/**
 * Guarded Suspension，核心思想是仅当服务进程准备好时才提供服务
 * 
 * @author zealot
 */
public class Main {

	public static void main(String[] args) {
		RequestQueue<Request> queue = new RequestQueue<Request>();

		// 服务进程开启
		for (int i = 0; i < 10; i++) {
			new Thread(new ServerThread(queue), "ServerThread-" + i).start();
		}

		// 请求进程开启
		for (int i = 0; i < 10; i++) {
			new Thread(new ClientThread(queue), "ClientThread-" + i).start();
		}
	}
}
