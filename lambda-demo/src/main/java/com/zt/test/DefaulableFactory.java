package com.zt.test;

import java.util.function.Supplier;

public interface DefaulableFactory {
	/**
	 * Interfaces now allow static methods
	 * 
	 * 接口可以声明（并且可以提供实现）静态方法
	 * 
	 * @param supplier
	 * @return
	 */
	static IDefaulable create(Supplier<IDefaulable> supplier) {
		return supplier.get();
	}
}