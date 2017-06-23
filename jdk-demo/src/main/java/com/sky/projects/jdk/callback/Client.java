package com.sky.projects.jdk.callback;

/**
 * 模拟客户端类
 * 
 * @author zt
 *
 */
public class Client implements CallBackable {

	// 服务器实例
	private Server server;

	/**
	 * 客户端发送信息给服务端
	 * 
	 * @param msg
	 */
	public void sendMsg(final String msg) {
		System.out.println("客户端：发送的消息为：" + msg);

		// 使用线程来完成异步发送信息
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
	public void call(String status) {
		System.out.println("客户端：服务端回调状态为：" + status);
	}

	public Client(Server server) {
		this.server = server;
	}
}