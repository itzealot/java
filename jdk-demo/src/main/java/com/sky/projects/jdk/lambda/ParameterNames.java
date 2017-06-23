package com.sky.projects.jdk.lambda;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 参数名字
 * 
 * @author zealot
 *
 */
public class ParameterNames {

	public static void main(String[] args) throws Exception {
		// 获取 Method 对象
		Method method = ParameterNames.class.getMethod("main", String[].class);

		// 获取参数
		for (final Parameter parameter : method.getParameters()) {
			System.out.println("Parameter: " + parameter.getName());
		}
	}
}