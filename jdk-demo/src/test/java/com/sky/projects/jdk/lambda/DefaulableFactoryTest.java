package com.sky.projects.jdk.lambda;

import org.junit.Test;

public class DefaulableFactoryTest {
	
	@Test
	public void test() {

		// 创建 DefaultableImpl 对象，使用 DefaultableImpl::new
		IDefaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
		System.out.println(defaulable.notRequired());

		// 创建 OverridableImpl 对象，使用 OverridableImpl::new
		defaulable = DefaulableFactory.create(OverridableImpl::new);
		System.out.println(defaulable.notRequired());
	}
}
