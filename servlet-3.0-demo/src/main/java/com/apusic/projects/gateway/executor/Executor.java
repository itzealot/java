package com.apusic.projects.gateway.executor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;

import com.apusic.projects.gateway.httpclients.HttpClientHandler;
import com.apusic.projects.gateway.httpclients.IHttpClientHandler;

/**
 * 异步调用业务进程调用
 * 
 * Title: Executor.<br />
 * Description: .<br />
 * 
 * @author zengtao
 * @date 2015年9月8日
 */
public class Executor implements Runnable {

	// 异步上下文
	private AsyncContext ctx = null;

	/**
	 * 使用异步上下文对象创建Executor 对象
	 * 
	 * Title: .<br />
	 * Description: .<br />
	 * 
	 * @param ctx
	 */
	public Executor(AsyncContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void run() {
		ServletRequest request = ctx.getRequest();

		String id = (String) request.getAttribute("id");
		String protocol = (String) request.getAttribute("procotol");
		String url = (String) request.getAttribute("url");

		// 需要判断是否为空值，此处省略

		IHttpClientHandler handler = new HttpClientHandler(id, protocol, url);
		String message = handler.doHandler();

		// To setAttribute into
		request.setAttribute("message", message);

		// To dispatch the jsp page
		ctx.dispatch("/success.jsp");

	}
}