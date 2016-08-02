package com.sky.projects.web.common.mvc;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理特殊字符的过滤器
 * 
 * @author zt
 *
 */
public class HtmlToStringFilter extends SkyFilter {

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new HTMLCharacterRequest(request), response);
	}

}