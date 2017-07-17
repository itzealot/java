package com.sky.projects.jdk.lambda;

@FunctionalInterface
public interface FunctionalInterfaceService {

	/**
	 * 抽象方法，仅有一个未实现的方法
	 */
	void func();

	// 即 java.lang.Object 中的方法(必须是 Object 中的 public 方法，不能为 protected
	// 的方法)，该方法不是抽象方法
	boolean equals(Object obj);

	/**
	 * default 方法，不是抽象方法(有函数体)，即接口默认实现方法，实现类可进行覆盖
	 */
	default void defaultMethod() {
		// TODO 方法业务处理
	}

	/**
	 * static 方法，不是抽象方法(有函数体)，即接口的 static 方法，可以通过接口直接调用
	 */
	static void staticMethod() {
		// TODO 方法业务处理
	}
}