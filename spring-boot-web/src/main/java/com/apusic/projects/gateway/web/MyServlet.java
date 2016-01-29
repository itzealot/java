package com.apusic.projects.gateway.web;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component("myServlet")
public class MyServlet implements Servlet {
	/**
	 * 
	 * @see javax.servlet.Servlet#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("destroy...");
	}

	/**
	 * @return
	 * @see javax.servlet.Servlet#getServletConfig()
	 */
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	/**
	 * @return
	 * @see javax.servlet.Servlet#getServletInfo()
	 */
	@Override
	public String getServletInfo() {
		return null;
	}

	/**
	 * @param arg0
	 * @throws ServletException
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("servlet init...");
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.Servlet#service(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service...");
	}
}