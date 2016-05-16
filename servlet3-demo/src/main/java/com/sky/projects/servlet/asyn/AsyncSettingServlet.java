package com.sky.projects.servlet.asyn;

import java.io.IOException;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用在web.xml 中配置的方式实现异步Servlet.<br />
 * 
 * @author zengtao
 * @date 2015年8月27日
 */
public class AsyncSettingServlet extends HttpServlet {

	private static final long serialVersionUID = -2673170561403191890L;

	public AsyncSettingServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("进入Servlet的时间：" + new Date() + ".<br />");

		// 在子线程中执行业务调用，并由其负责输出响应，主线程退出
		// 创建AsyncContext，开始异步调用
		AsyncContext ctx = request.startAsync();

		// 设置异步调用的超时时长
		ctx.setTimeout(10 * 1000);

		// 启动异步调用的线程,模拟业务处理
		ctx.start(new Executor(ctx));

		System.out.println("结束Servlet的时间：" + new Date() + ".<br />");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
