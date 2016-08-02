package com.sky.projects.web.common.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HTMLCharacterRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public HTMLCharacterRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// 获取参数列表,对需要增强方法进行覆盖
		Map<String, String[]> parameterMap = request.getParameterMap();

		for (String parameterName : parameterMap.keySet()) {
			String[] values = parameterMap.get(parameterName);

			if (values != null) {
				for (int i = 0, len = values.length; i < len; i++) {
					// 特殊字符转义处理，方便在 Html 中显示
					values[i] = HtmlStringUtil.filter(values[i]);
				}
			}
		}

		return parameterMap;
	}

	@Override
	public String getParameter(String name) {
		String[] values = getParameterMap().get(name);
		return values == null ? null : values[0]; // 取回参数的第一个值
	}

	@Override
	public String[] getParameterValues(String name) {
		return getParameterMap().get(name);
	}

}