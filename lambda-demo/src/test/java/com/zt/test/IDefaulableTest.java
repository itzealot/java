package com.zt.test;

import org.junit.Test;

/**
 * 测试
 * 
 * @author zt
 *
 */
public class IDefaulableTest {

	@Test
	public void test() {
		IDefaulable defaulable1 = new DefaultableImpl();
		System.out.println(defaulable1.notRequired());

		IDefaulable defaulable2 = new OverridableImpl();
		System.out.println(defaulable2.notRequired());
	}
}
