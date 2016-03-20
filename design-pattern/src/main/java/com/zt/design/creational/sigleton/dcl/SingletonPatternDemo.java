package com.zt.design.creational.sigleton.dcl;

/**
 * 3.2、懒汉式，线程安全.<br />
 * 测试类SingletonPatternDemo
 * 
 * @author zengtao
 *
 */
public class SingletonPatternDemo {

	public static void main(String[] args) {

		// 获取唯一可用的对象
		Singleton object = Singleton.getSingleton();

		System.out.println(object);
	}
}