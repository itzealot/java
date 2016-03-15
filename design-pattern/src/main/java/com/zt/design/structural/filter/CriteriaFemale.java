package com.zt.design.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤非女性的实现类
 * 
 * @author zengtao
 *
 */
public class CriteriaFemale implements Criteria {

	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> femalePersons = new ArrayList<Person>();

		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("FEMALE")) {
				femalePersons.add(person);
			}
		}

		return femalePersons;
	}
}
