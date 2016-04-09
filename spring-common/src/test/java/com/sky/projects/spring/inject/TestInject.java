package com.sky.projects.spring.inject;

import javax.inject.Inject;

import org.junit.Test;

import com.sky.projects.spring.inject.entity.Person;

public class TestInject {
	@Inject
	Person person;

	@Test
	public void testInject() {
		System.out.println(person);
	}
}
