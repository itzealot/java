package com.sky.projects.jdk.jvm.init;

import junit.framework.TestCase;

public class TestClassInit extends TestCase {

	/**
	 * 1.父类静态代码块初始化.<br>
	 * 2.子类静态代码块初始化.<br>
	 * 3.父类成员代码块初始化.<br>
	 * 4.父类构造方法初始化.<br>
	 * 5.子类成员代码块初始化.<br>
	 * 6.子类构造方法初始化.<br>
	 */
	public void testNewInstanceInit() {
		new SubClass();
	}

	/**
	 * 类初始化触发条件:<br>
	 * 1. 调用了new.<br>
	 * 2. 反射调用了类中的方法.<br>
	 * 3. 子类调用了初始化.<br>
	 * 4. JVM启动过程中指定的初始化类.<br>
	 */
	public void testClassStaticInit() {
		// 以下调用不会触发类初始化
		System.out.println(SubClass.class);
		System.out.println(SubClass.class.getName());

		System.out.println(SupperClass.class);
		System.out.println(SupperClass.class.getName());

		System.out.println(SubClass.DEFAULT_VALUE);
		System.out.println(SupperClass.MAX_COUNTS);
	}

	/**
	 * 调用父类的static方法父类会进行静态代码块的初始化
	 */
	public void testStaticMethodInvoke() {
		// 调用父类的静态方法
		SupperClass.print("sss");
	}

	/**
	 * 调用子类的static方法，先进行父类的静态代码块而后再进行子类的静态代码块初始化
	 */
	public void testSubClassStaticMethodInvoke() {
		// 调用子类的静态方法
		SubClass.print("sss");
	}
}
