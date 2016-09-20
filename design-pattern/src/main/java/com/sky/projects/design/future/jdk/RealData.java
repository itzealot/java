package com.sky.projects.design.future.jdk;

import java.util.concurrent.Callable;

import com.sky.projects.design.common.Threads;

/**
 * RealData 使用JDK 内置Future 模式实现
 * 
 * @author zealot
 */
public class RealData implements Callable<String> {

	private String param;

	public RealData(String param) {
		this.param = param;
	}

	@Override
	public String call() throws Exception {
		System.out.println("start execute call.........");
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < 20; i++) {
			buffer.append(param);
			Threads.sleep(150);
		}

		System.out.println("finish execute call.........");
		return buffer.toString();
	}

}
