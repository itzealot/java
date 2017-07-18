package com.sky.projects.design.common;

/**
 * 不变模式 ，实现方式：<br>
 * 1. 对象的所有属性被final修饰，确保不能被更改；<br>
 * 2. final修饰所有属性，意味着不支持提供setXXX方法等修改属性的方法，可以提供getXXX等获取属性的方法；<br>
 * 3. 提供至少一个构造函数，给final修饰的变量全部设置初始值；<br>
 * 4. 确保无子类可以修改其行为，即类使用final修饰，不支持被继承；
 * 
 * @author zealot
 */
public final class UnmodifiableClassImpl {
	private final String name;
	private final int age;

	public UnmodifiableClassImpl(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}