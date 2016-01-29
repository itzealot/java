package com.zt.test.entity;

import javax.inject.Named;

@Named("person")
public class Person {
	private String name;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name) {
		System.out.println("---这是人的构造函数---");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

}