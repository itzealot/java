package com.zt.design.structural.filter;

import java.util.List;

/**
 * 多个过滤条件进行与运算过滤的实现类
 * 
 * @author zengtao
 *
 */
public class AndCriteria implements Criteria {

	private Criteria criteria;

	private Criteria otherCriteria;

	public AndCriteria(Criteria criteria, Criteria otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}

	public List<Person> meetCriteria(List<Person> persons) {
		return otherCriteria.meetCriteria(criteria.meetCriteria(persons));
	}
}
