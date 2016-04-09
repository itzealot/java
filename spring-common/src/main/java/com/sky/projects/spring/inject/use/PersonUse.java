package com.sky.projects.spring.inject.use;

import javax.inject.Inject;

import com.sky.projects.spring.inject.entity.Person;

public class PersonUse {

	@Inject
	private Person person;

	public void usePerson() {
		System.out.println(".........................");
		System.out.println(person);
	}
}
