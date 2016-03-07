package com.sky.projects.web.common.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JavaWeb 抽象 HttpServlet，默认只需实现 doGet 方法，doPost 方法调用的是 doGet
 * 
 * @author zt
 *
 */
public abstract class SkyHttpServlet extends HttpServlet {

	private static final long serialVersionUID = -7882853287189277573L;

	/**
	 * doGet 方法，对请求进行处理
	 */
	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
