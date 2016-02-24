package com.zt.test;

/**
 * 测试类
 * 
 * @author zt
 *
 */
public class CallBackTest {
	public static void main(String[] args) {
		Server server = new Server();
		Client client = new Client(server);

		// 客户端发送信息给服务器端
		client.sendMsg("Server,Hello~");
	}
}