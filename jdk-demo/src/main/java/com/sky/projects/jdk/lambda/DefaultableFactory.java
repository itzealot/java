package com.sky.projects.jdk.lambda;

import java.util.function.Supplier;

public interface DefaultableFactory {

	/**
	 * 接口可以声明(并且可以提供实现)静态方法
	 * 
	 * @param supplier
	 * @return
	 */
	static Defautlable create(Supplier<Defautlable> supplier) {
		return supplier.get();
	}
}