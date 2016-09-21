package com.sky.projects.design.future.demo;

import com.sky.projects.design.common.Threads;

/**
 * RealData 构建很慢
 * 
 * @author zealot
 */
public class RealData implements Data {
	private String result;
	private String name;

	public RealData() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			Threads.sleep(150);
			buffer.append(i);
		}

		System.out.println("finish load the RealData.....");
		this.result = buffer.toString();
	}

	public RealData(String name) {
		this.name = name;
	}

	@Override
	public String getResult() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			Threads.sleep(150);
			buffer.append(name);
		}

		System.out.println("finish load the RealData.....");
		this.result = buffer.toString();
		return result;
	}

	@Override
	public void setRealData(RealData data) {
	}

}
