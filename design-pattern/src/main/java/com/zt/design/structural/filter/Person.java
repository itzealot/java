package com.zt.design.structural.filter;

public class Person {
	// 名称
	private String name;

	// 男士或女士
	private String gender;

	// 婚姻状态
	private String maritalStatus;

	public Person() {
	}

	public Person(String name, String gender, String maritalStatus) {
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}
}
