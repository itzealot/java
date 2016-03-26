package com.sky.projects.servlet;

import java.util.Date;

public class NoExecutor {

	public void run() {
		try {
			System.out.println("业务开始时间" + new Date() + ".");

			// 等待十秒钟，以模拟业务方法的执行
			Thread.sleep(10000);

			System.out.println("业务结束时间" + new Date() + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}