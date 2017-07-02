package com.sky.projects.jdk.jvm.init;

public class SubClass extends SupperClass {

	public static final int DEFAULT_VALUE = 100;

	static {
		System.out.println("SubClass 静态代码块初始化.");
	}

	{
		System.out.println("SubClass 成员代码块初始化.");
	}

	public SubClass() {
		super();
		System.out.println("SubClass 构造方法初始化");
	}

	public static void print(String message) {
		System.out.println("message:" + message);
	}
}
