package com.sky.projects.design.future.demo;

/**
 * 客户端(获取请求)
 * 
 * @author zealot
 */
public class Client {

	/**
	 * 接收请求构建 RealData 并快速返回轻量级的 FutureData
	 * 
	 * @param req
	 * @return
	 */
	public Data request(final String req) {
		final FutureData data = new FutureData();

		// 开启异步线程构建 RealData，构建完成后并通知 FutureData
		new Thread(new Runnable() {
			@Override
			public void run() {
				RealData realData = new RealData(req);
				data.setRealData(realData);
			}
		}).start();

		return data;
	}
}
