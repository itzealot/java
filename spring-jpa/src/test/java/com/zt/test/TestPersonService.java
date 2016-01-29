package com.zt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.entity.Person;
import com.zt.service.PersonService;

public class TestPersonService {
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testSavePersons() {
		PersonService personService = applicationContext.getBean(PersonService.class);

		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(null, "iii", "iii@qq.com", 22));
		persons.add(new Person(null, "jjj", "jjj@qq.com", 33));

		personService.savePersons(persons);
	}

	@Test
	public void testSavePersons2() {
		PersonService personService = applicationContext.getBean(PersonService.class);

		Person person1 = new Person(null, "iiiii", "iiii@qq.com", 22);
		Person person2 = new Person(null, "jjjjj", "jjjj@qq.com", 33);

		personService.savePersons(person1, person2);
	}
}
