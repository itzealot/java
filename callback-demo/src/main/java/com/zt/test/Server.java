package com.zt.test;

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
	 * @param csCallBack
	 * @param msg
	 */
	public void getClientMsg(CSCallBack csCallBack, String msg) {
		System.out.println("服务端：服务端接收到客户端发送的消息为:" + msg);

		// 模拟服务端需要对数据处理
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("服务端:数据处理成功，返回成功状态 200");
		String status = "200";

		// 回调给客户端
		csCallBack.process(status);
	}
}