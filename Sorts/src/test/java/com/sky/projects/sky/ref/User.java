package com.sky.projects.sky.ref;

import java.util.Date;

public class User {
	private Long id;
	private String name;
	private String password;
	private Date date;
	private Integer age;
	private int count;
	private String remark;

	public User() {
		super();
	}

	public User(Long id, String name, String password, Date date, Integer age,
			String remark, int count) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.date = date;
		this.age = age;
		this.remark = remark;
		this.count = count;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", date=" + date + ", age=" + age + ", count=" + count
				+ ", remark=" + remark + "]";
	}

}
