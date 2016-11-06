package com.sky.projects.jdk.jvm;

/**
 * 测试栈的深度,使用 -Xss1M 设置栈的大小为 1M
 * 
 * @author zealot
 */
public class TestStackDepth {

	private static int count = 0;

	public static void main(String[] args) {
		try {
			count();
		} catch (Throwable e) {
			System.out.println("stack depth : " + count);
		}
	}

	public static void count() {
		count++;
		count();
	}
}
