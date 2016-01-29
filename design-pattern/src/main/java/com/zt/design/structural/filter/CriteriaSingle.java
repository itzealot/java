package com.zt.design.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤得到所有的单身List<Person>
 * 
 * @author zengtao
 *
 */
public class CriteriaSingle implements Criteria {

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
