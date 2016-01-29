package com.zt.design.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤得到所有的男性List<Person>
 * 
 * @author zengtao
 *
 */
public class CriteriaMale implements Criteria {

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
