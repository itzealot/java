package com.zt.test.bean;

public class Person {
	private String id;
	private String name;
	private String password;
	private String remark;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String id, String name, String password, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", password=" + password + ", remark=" + remark + "]";
	}
	
	public static void test() {
		System.out.println("test..");
	}
}
