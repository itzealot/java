package com.sky.projects.spring.inject.entity;

import javax.inject.Inject;
import javax.inject.Named;

@Named("house")
public class House {
	@Inject
	private Person owner;

	public House() {
		System.out.println("---这是房屋构造函数---");
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "House [owner=" + owner + "]";
	}

}