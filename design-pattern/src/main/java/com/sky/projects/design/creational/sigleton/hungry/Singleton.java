package com.sky.projects.design.creational.sigleton.hungry;

/**
 * 3.3、饿汉式
 * 
 * 3.3.1 是否 Lazy 初始化：否
 * 
 * 3.3.2 是否多线程安全：是
 * 
 * 3.3.3 实现难度：易
 * 
 * 3.3.4 描述：这种方式比较常用，但容易产生垃圾对象。<br />
 * 
 * 3.3.5 优点：没有加锁，执行效率会提高。 <br />
 * 
 * 3.3.6 缺点：类加载时就初始化，浪费内存。 它基于 classloder 机制避免了多线程的同步问题，不过，instance
 * 在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
 * 
 * @author zealot
 *
 */
public class Singleton {

	// 使用静态成员变量保存单例，类加载时就会初始化
	private static Singleton instance = new Singleton();

	/**
	 * 获取单例实例
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		return instance;
	}

	private Singleton() {
	}
}