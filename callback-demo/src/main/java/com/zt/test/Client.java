package com.zt.test;

/**
 * 模拟客户端类
 * 
 * @author zt
 *
 */
public class Client implements CSCallBack {

	// 服务器实例
	private Server server;

	public void sendMsg(final String msg) {
		System.out.println("客户端：发送的消息为：" + msg);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				// 服务器端处理客户端消息
				server.getClientMsg(Client.this, msg);
			}
		});

		thread.start();

		System.out.println("客户端：异步发送成功");
	}

	@Override
	public void process(String status) {
		System.out.println("客户端：服务端回调状态为：" + status);
	}

	public Client(Server server) {
		this.server = server;
	}
}