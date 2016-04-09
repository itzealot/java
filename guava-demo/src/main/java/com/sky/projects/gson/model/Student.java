package com.sky.projects.gson.model;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 1. 有时候我们不需要把实体的所有属性都导出,只想把一部分属性导出为Json.
 * 
 * 2. 有时候我们的实体类会随着版本的升级而修改.
 * 
 * 3. 有时候我们想对输出的json默认排好格式.
 * 
 * @author zt
 *
 */
public class Student {
	private int id;

	@Expose
	private String name;

	@Expose
	@SerializedName("bir")
	private Date birthDay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return "Student [birthDay=" + birthDay + ", id=" + id + ", name="
				+ name + "]";
	}

}