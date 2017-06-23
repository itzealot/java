package com.sky.projects.jdk.lambda;

import java.util.function.Supplier;

import org.junit.Test;

public class DefaultableFactoryTest {

	@Test
	public void test() {
		// 创建 DefaultableImpl 对象，使用 DefaultableImpl::new
		Defautlable defaulable = DefaultableFactory.create(DefaultableImpl::new);
		System.out.println(defaulable.notRequired());

		// 创建 OverridableImpl 对象，使用 OverridableImpl::new
		defaulable = DefaultableFactory.create(OverridableImpl::new);
		System.out.println(defaulable.notRequired());
	}

	@Test
	public void testNew() {
		// Create a object
		Supplier<DefaultableImpl> supplier = DefaultableImpl::new;
		System.out.println(supplier.get());

		// must be a function interface
		Supplier<String> string = String::new;
		System.out.println(string.get().isEmpty());
	}
}
