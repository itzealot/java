package com.zt.design.structural.filter;

import java.util.List;

/**
 * 含过滤方法的接口Criteria
 * 
 * @author zengtao
 *
 */
public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}
