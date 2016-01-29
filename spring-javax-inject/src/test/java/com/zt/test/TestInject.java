package com.zt.test;

import javax.inject.Inject;

import org.junit.Test;

import com.zt.test.entity.Person;

public class TestInject {
	@Inject
	Person person;

	@Test
	public void testInject() {
		System.out.println(person);
	}
}
