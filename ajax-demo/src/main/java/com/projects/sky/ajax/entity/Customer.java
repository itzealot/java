package com.projects.sky.ajax.entity;

public class Customer {
	private String id;
	private String name;
	private String date;
	private String city;

	public Customer(String id, String name, String date, String city) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.city = city;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}
}
