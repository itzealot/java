package com.zt.design.creational.sigleton.lazy.unsafe;

/**
 * 3.1、懒汉式，线程不安全.<br />
 * 测试类SingletonPatternDemo
 * 
 * @author zengtao
 *
 */
public class SingletonPatternDemo {
	public static void main(String[] args) {
		// 获取唯一可用的对象
		Singleton object = Singleton.getInstance();
		System.out.println(object);
	}
}