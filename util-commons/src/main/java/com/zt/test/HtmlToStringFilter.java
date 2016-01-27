package com.zt.test;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理特殊字符的过滤器
 * 
 * @author a
 *
 */
public class HtmlToStringFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// String words = request.getParameter("word");
		// String filter = HtmlWordsToStringUtils.filter(words);
		HTMLCharacterRequest myRequest = new HTMLCharacterRequest(request);
		chain.doFilter(myRequest, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

class HTMLCharacterRequest extends HttpServletRequestWrapper {
	HttpServletRequest request;

	public HTMLCharacterRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	// 对需要增强方法 进行覆盖
	@Override
	public Map<String, String[]> getParameterMap() {

		// 获取参数列表
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String parameterName : parameterMap.keySet()) {
			String[] values = parameterMap.get(parameterName);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					try {
						// 特殊字符转义处理，方便在Html中显示
						values[i] = HtmlStringUtil.filter(values[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return parameterMap;
	}

	@Override
	public String getParameter(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		if (values == null) {
			return null;
		}
		return values[0]; // 取回参数的第一个值
	}

	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}

}