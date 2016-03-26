package com.sky.projects.servlet.asyn;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;

/**
 * 异步调用业务进程调用
 * 
 * @author zengtao
 * @date 2015年8月28日
 */
public class Executor implements Runnable {

	// 异步上下文
	private AsyncContext ctx = null;

	public Executor(AsyncContext ctx) {
		this.ctx = ctx;
	}

	public void run() {
		try {
			// 等待十秒钟，以模拟业务方法的执行
			Thread.sleep(5 * 1000);

			ServletRequest request = ctx.getRequest();

			// To setAttribute into
			request.setAttribute("username", "I am User");

			// To dispatch the jsp page
			ctx.dispatch("/success.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}