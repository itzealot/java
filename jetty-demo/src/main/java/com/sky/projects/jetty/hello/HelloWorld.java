package com.sky.projects.jetty.hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Jetty HelloWorld
 * 
 * @author zealot
 *
 */
public class HelloWorld extends AbstractHandler {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		server.setHandler(new HelloWorld());

		server.start();
		server.join();
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// content type
		response.setContentType("text/html;charset=utf-8");

		// response status
		response.setStatus(HttpServletResponse.SC_OK);

		baseRequest.setHandled(true);

		response.getWriter().println("<h1>HelloWorld</h1>");
	}

}