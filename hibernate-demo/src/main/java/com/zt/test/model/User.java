package com.zt.test.model;

import java.util.Date;

public class User {
	private Integer id;
	private String title;
	private String author;
	private Date date;
	private String desc;

	public User(Integer id, String title, String author, Date date, String desc) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
		this.desc = desc;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author + ", date=" + date + ", desc=" + desc + "]";
	}

}
