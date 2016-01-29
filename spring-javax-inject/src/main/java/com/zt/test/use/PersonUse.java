package com.zt.test.use;

import javax.inject.Inject;

import com.zt.test.entity.Person;

public class PersonUse {

	@Inject
	private Person person;

	public void usePerson() {
		System.out.println(".........................");
		System.out.println(person);
	}
}
