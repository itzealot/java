package com.sky.projects.jdk.java8.function;

@FunctionalInterface
public interface MyFunction {

	void run();

	// 只支持Object的public方法定义未实现
	String toString(); // same to Object.toString

	int hashCode(); // same to Object.hashCode

	boolean equals(Object obj); // same to Object.equals

	// error define
	// Object clone(); // Object.clone is protected
}
