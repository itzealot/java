package com.zt.entity;

import java.util.Date;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class User implements Comparable<User> {
	private Long id;
	private String name;
	private String password;
	private Date birthday;
	private String remark;
	private boolean left = false;
	private boolean right = false;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name, String password, Date birthday, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.remark = remark;
	}

	public User(Long id, String name, String password, Date birthday, String remark, boolean left, boolean right) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.remark = remark;
		this.left = left;
		this.right = right;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", birthday=" + birthday + ", remark="
				+ remark + ", left=" + left + ", right=" + right + "]";
	}

	/**
	 * 1. 使用 ComparisonChain 来实现多维度排序.<br />
	 * 2. ComparisonChain 执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略.<br />
	 * 3. 以 start 方法开始，以 result 方法结束
	 */
	@Override
	public int compareTo(User that) {
		return ComparisonChain.start().compare(this.id, that.id).compare(this.name, that.name)
				.compare(this.birthday, that.birthday, Ordering.natural().nullsLast()).compareFalseFirst(left, right)
				.result();
	}

}
