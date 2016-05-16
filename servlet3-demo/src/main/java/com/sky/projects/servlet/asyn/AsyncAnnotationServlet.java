package com.sky.projects.servlet.asyn;

import java.io.IOException;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用注解的方式实现异步Servlet.<br />
 * urlPatterns: url.<br />
 * asyncSupported : true 表示支持异步；为false表示不支持异步；默认是不支持异步.<br />
 * 
 * @author zt
 * @date 2015年8月27日
 */
@WebServlet(urlPatterns = "/demo", asyncSupported = true)
public class AsyncAnnotationServlet extends HttpServlet {

	private static final long serialVersionUID = -2673170561403191890L;

	public AsyncAnnotationServlet() {
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
