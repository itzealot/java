package com.sky.projects.design.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤非女性的实现类
 * 
 * @author zealot
 *
 */
public class CriteriaFemale implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> females = new ArrayList<>();

		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("FEMALE")) {
				females.add(person);
			}
		}

		return females;
	}
}
