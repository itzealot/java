package com.sky.projects.design.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤非单身者的实现类
 * 
 * @author zealot
 *
 */
public class CriteriaSingle implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> singlePersons = new ArrayList<Person>();

		for (Person person : persons) {
			if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
				singlePersons.add(person);
			}
		}

		return singlePersons;
	}
}
