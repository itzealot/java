package com.sky.projects.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用在web.xml 中配置的方式实现异步Servlet
 * 
 * @author zengtao
 * @date 2015年8月27日
 */
@WebServlet(urlPatterns = "/NoAsyncServlet", asyncSupported = false)
public class NoAsyncServlet extends HttpServlet {

	private static final long serialVersionUID = -2673170561403191890L;

	public NoAsyncServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("进入Servlet的时间：" + new Date() + ".");
		out.flush();

		new NoExecutor().run();

		out.println("结束Servlet的时间：" + new Date() + ".");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
