package com.sky.projects.design.creational.sigleton.lazy.unsafe;

/**
 * 3.1、懒汉式，线程不安全
 * 
 * 3.1.1 是否 Lazy 初始化：是
 * 
 * 3.1.2 是否多线程安全：否
 * 
 * 3.1.3 实现难度：易
 * 
 * 3.1.4 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 * 
 * @author zengtao
 *
 */
public class Singleton {
	// 静态成员变量，Singleton 实例对象
	private static Singleton instance = null;

	/**
	 * 获取实例，非线程安全；多线程下 可能创建多个实例.
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		return instance == null ? new Singleton() : instance;
	}

	private Singleton() {
		super();
	}
}
