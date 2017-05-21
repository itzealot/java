package com.sky.projects.design.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤非男性的实现类
 * 
 * @author zealot
 *
 */
public class CriteriaMale implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> malePersons = new ArrayList<Person>();

		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("MALE")) {
				malePersons.add(person);
			}
		}

		return malePersons;
	}
}
