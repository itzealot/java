package com.sky.projects.design.creational.sigleton.serial;

import java.io.Serializable;

/**
 * 代码中，通过反射机制，仍然可以通过调用私有构造方法生成多个单例。
 * 
 * 可被串行化的单例
 * 
 * @author zt
 */
public class Singleton implements Serializable {
	private static final long serialVersionUID = 9085535754030569982L;

	private static Singleton instance = new Singleton();

	/**
	 * 获取单例
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		return instance;
	}

	/**
	 * 防止生成新的实例，总是返回当前对象
	 * 
	 * 串行化与反串行化，写入单例到文件并读取出时，如果没有该方法则会抛出异常
	 * 
	 * 实现了私有的 readResolve 后，readObject 形同虚设，直接使用 readResolve
	 * 的返回值替换原本的返回值，从而在形式上构造了单例
	 * 
	 * @return
	 */
	private Object readResolve() {
		return instance;
	}

	private Singleton() {
	}
}
