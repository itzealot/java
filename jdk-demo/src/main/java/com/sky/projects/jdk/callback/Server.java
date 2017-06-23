package com.sky.projects.jdk.callback;

import com.sky.projects.jdk.thread.Threads;

/**
 * 模拟服务端类
 * 
 * @author zt
 *
 */
public class Server {

	/**
	 * 处理客户端传来的消息，并执行回调函数
	 * 
	 * @param call
	 * @param msg
	 */
	public void getClientMsg(CallBackable call, String msg) {
		System.out.println("服务端：服务端接收到客户端发送的消息为:" + msg);

		// 模拟服务端需要对数据处理
		Threads.sleep(5 * 1000);

		System.out.println("服务端:数据处理成功，返回成功状态 200");
		String status = "200";

		// 执行回调函数
		call.call(status);
	}
}