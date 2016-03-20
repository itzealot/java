package com.zt.design.creational.sigleton.lazy.safe;

/**
 * 3.2、懒汉式，线程安全
 * 
 * 3.2.1 是否 Lazy 初始化：是
 * 
 * 3.2.2 是否多线程安全：是
 * 
 * 3.2.3 实现难度：易
 * 
 * 3.2.4 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。 <br />
 * 
 * 3.2.5 优点：第一次调用才初始化，避免内存浪费。 <br />
 * 
 * 3.2.6 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。 getInstance()
 * 的性能对应用程序不是很关键（该方法使用不太频繁）。
 * 
 * @author zengtao
 *
 */
public class Singleton {

	private static Singleton instance;

	private Singleton() {
	}

	/**
	 * 获取单例实例变量.<br />
	 * 使用synchronized 关键字来保证线程安全.<br />
	 * 
	 * @return
	 */
	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}

		return instance;
	}
}
