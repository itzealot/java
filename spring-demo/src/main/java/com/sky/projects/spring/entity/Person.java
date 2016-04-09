package com.sky.projects.spring.entity;

import java.util.List;

public class Person {
	private String name;

	private List<Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", cars=" + cars + "]";
	}

}
