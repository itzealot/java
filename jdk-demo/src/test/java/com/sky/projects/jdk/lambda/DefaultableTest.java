package com.sky.projects.jdk.lambda;

import org.junit.Test;

/**
 * 测试
 * 
 * @author zt
 *
 */
public class DefaultableTest {

	@Test
	public void test() {
		Defautlable defaulable1 = new DefaultableImpl();
		System.out.println(defaulable1.notRequired());

		Defautlable defaulable2 = new OverridableImpl();
		System.out.println(defaulable2.notRequired());
	}
}
