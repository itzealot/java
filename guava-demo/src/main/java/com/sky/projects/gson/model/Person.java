package com.sky.projects.gson.model;

import java.io.Serializable;

public class Person extends BaseModel<Long> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8838426694022091527L;
	private Long id;
	private String name;
	private int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
