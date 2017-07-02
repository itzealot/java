package com.sky.projects.jdk.jvm.init;

public class SupperClass {

	public static final int MAX_COUNTS = 100;
	public static final int MIN_COUNTS = 10;

	static {
		System.out.println("SuperClass 静态代码块初始化.");
	}

	{
		System.out.println("SuperClass 成员代码块初始化.");
	}

	public SupperClass() {
		super();
		System.out.println("SuperClass 构造方法初始化");
	}

	public static void print(String message) {
		System.out.println("message:" + message);
	}

}
