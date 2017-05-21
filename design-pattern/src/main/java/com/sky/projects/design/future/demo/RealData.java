package com.sky.projects.design.future.demo;

import com.sky.projects.design.common.Threads;

/**
 * RealData 构建很慢
 * 
 * @author zealot
 */
public class RealData {
	private String result;

	/**
	 * 构建RealData，耗时操作
	 */
	public RealData(String req) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			Threads.sleep(150);
			buffer.append(req);
		}

		System.out.println("finish load the RealData.....");
		this.result = buffer.toString();
	}

	public String getResult() {
		return result;
	}

}
