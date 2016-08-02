package com.sky.projects.web.common.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JavaWeb Abstract HttpServlet
 * 
 * @author zealot
 *
 */
public abstract class SkyHttpServlet extends HttpServlet {

	private static final long serialVersionUID = -7882853287189277573L;

	/**
	 * doGet 方法，对请求进行处理，默认实现调用 {@link SkyHttpServlet#doPost}
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * doPost 方法，对请求进行处理
	 */
	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
