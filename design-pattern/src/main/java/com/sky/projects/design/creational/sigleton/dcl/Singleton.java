package com.sky.projects.design.creational.sigleton.dcl;

/**
 * 3.4、双检锁/双重校验锁（DCL，即 double-checked locking）
 * 
 * 3.4.1 JDK 版本：JDK1.5 起
 * 
 * 3.4.2 是否 Lazy 初始化：是
 * 
 * 3.4.3 是否多线程安全：是
 * 
 * 3.4.4 实现难度：较复杂
 * 
 * 3.4.5 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。 getInstance() 的性能对应用程序很关键。
 * 
 * @author zengtao
 *
 */
public class Singleton {
	/*
	 * 1. volatile 关键字 ：用在多线程，同步变量。
	 * 
	 * 2. 线程为了提高效率，将某成员变量(如A)拷贝了一份(如B)，线程中对A的访问其实访问的是B。只在某些动作时才进行A和B的同步，
	 * 因此存在A和B不一致的情况。volatile就是用来避免这种情况的。volatile告诉jvm，
	 * 它所修饰的变量不保留拷贝，直接访问主内存中的(也就是上面说的A)。
	 * 
	 * 3. 这里用来修饰一个单例的实例变量。
	 */
	private volatile static Singleton singleton;

	/**
	 * 获取单例实例
	 * 
	 * @return
	 */
	public static Singleton getSingleton() {
		if (singleton == null) {
			// 使用 Singleton.class 对象来做锁
			synchronized (Singleton.class) {
				// 多线程下可能多个线程访问，可能实例变量同时为null，添加该限制可以避免
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}

		return singleton;
	}

	private Singleton() {
	}
}