package com.sky.projects.web.common.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 敏感字符过滤器
 * 
 * @author a
 *
 */
public class WordFilter implements Filter {
	List<String> seqing = new ArrayList<String>();
	List<String> fandong = new ArrayList<String>();
	List<String> zhengzhi = new ArrayList<String>();

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 用户提交的信息
		String word = request.getParameter("word");

		// 资源库比对 Pattern
		for (String des : seqing) {
			// 自定义正则
			Pattern pattern = Pattern.compile(des);// 正则匹配对象 资源库中每一个词汇 做成一个匹配正则

			// 和目标用户提交 数据进行比对
			Matcher m = pattern.matcher(word);

			// 判断是否 符合条件
			if (m.find()) {
				// m.find 找到... 非法言论 取出用户数据 m.group
				response.getWriter().print(
						"您的消息非法.....<font color='red' size='10px'>" + m.group().replace(m.group(), "传智播客") + "</font>");
				return;// 不放行!!!
			}

		}
		// for 循环完成 没有执行if 语句 放行
		chain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// 加载 敏感词汇读进来....
		// 1: 找路径... 循环读取.... List<String>
		// 类加载器 : 获取 src 路径 类路径
		String path = this.getClass().getClassLoader().getResource("").getPath().substring(1);

		File f = new File(path);

		if (f.exists()) {
			// 读取文件 文件过滤器 !! FileFilter xxx|1
			// 1: 获取该目录下所有文件
			File[] files = f.listFiles(new FileFilter() {
				// 文件过滤器的使用
				public boolean accept(File f) {
					// 过滤需要的文件
					return f.getName().endsWith(".txt");
				}
			});

			for (File file : files) {
				BufferedReader br = null;
				// 文件全部读取 放过不同对应集合中 1
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));// i/o
																										// 流的读取
					// 集合添加数据
					String line = null;
					while ((line = br.readLine()) != null) {
						String arr[] = line.split("\\|"); // xxx 1 2 3
						// 录入员 没有添加|
						if (arr.length > 1) {
							// 添加指定数组
							if ("1".equals(arr[1].trim())) {
								seqing.add(arr[0]);
							} else if ("2".equals(arr[1].trim())) {
								fandong.add(arr[0]);
							} else if ("3".equals(arr[1].trim())) {
								zhengzhi.add(arr[0]);
							}
						}

					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}