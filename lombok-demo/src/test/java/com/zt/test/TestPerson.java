package com.zt.test;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author zengtao
 *
 */
public class TestPerson {

	private Person person = null;

	@Before
	public void before() {
		person = new Person("1", "zhangsan", "aa");
	}

	@Test
	public void testDataAnnotation() {

		System.out.println(person.getId());

		System.out.println(person);
	}

	@Test
	public void testLog4j() {
		person.getLog().debug("id = " + person.getId());
	}
}
