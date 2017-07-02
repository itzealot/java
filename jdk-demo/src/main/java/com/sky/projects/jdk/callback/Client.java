package com.sky.projects.jdk.callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.ehcache.util.NamedThreadFactory;

/**
 * 模拟客户端类
 * 
 * @author zealot
 *
 */
public class Client {

	// 服务器实例
	private final Server server;
	private ExecutorService service = Executors.newSingleThreadExecutor(new NamedThreadFactory("Test-Thread", true));

	public Client(Server server) {
		this.server = server;
	}

	/**
	 * 客户端发送信息给服务端
	 * 
	 * @param msg
	 */
	public void sendMsg(final String msg) {
		System.out.println("客户端：发送的消息为：" + msg);

		// 调用服务端接口发现消息并执行回调函数
		service.execute(() -> server.getClientMsg((status) -> System.out.println("客户端：服务端回调状态为：" + status), msg));

		System.out.println("客户端：异步发送成功");
	}

}