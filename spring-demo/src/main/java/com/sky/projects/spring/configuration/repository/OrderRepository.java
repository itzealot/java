package com.sky.projects.spring.configuration.repository;

public class OrderRepository {
	private String string;

	public OrderRepository(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
