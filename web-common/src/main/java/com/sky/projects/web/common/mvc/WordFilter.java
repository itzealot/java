package com.sky.projects.web.common.mvc;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sky.projects.web.common.file.FileUtil;

/**
 * 敏感字符过滤器
 * 
 * @author zealot
 *
 */
public class WordFilter extends SkyFilter {
	private List<String> filters = new ArrayList<String>();

	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) {
		// 加载敏感词汇
		String path = this.getClass().getClassLoader().getResource("").getPath().substring(1);

		File f = new File(path);
		if (!f.exists()) {
			return;
		}

		File[] files = f.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().endsWith(".txt");
			}
		});

		filters.addAll(FileUtil.read(Arrays.asList(files)));
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String word = request.getParameter("word"); // 获取用户提交的信息

		for (String des : filters) {
			// 正则匹配对象资源库中每一个词汇 做成一个匹配正则,和目标用户提交数据比对
			Matcher m = Pattern.compile(des).matcher(word);

			if (m.find()) {// 判断不符合条件不放行,m.find 找到非法言论 取出用户数据 m.group
				response.getWriter().print(
						"您的消息非法<font color='red' size='10px'>" + m.group().replace(m.group(), "****") + "</font>");
				return;
			}
		}

		// 放行
		chain.doFilter(request, response);
	}

}