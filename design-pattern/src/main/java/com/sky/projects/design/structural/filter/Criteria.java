package com.sky.projects.design.structural.filter;

import java.util.List;

/**
 * 过滤接口
 * 
 * @author zealot
 *
 */
public interface Criteria {

	/**
	 * 根据传入的列表进行过滤，并返回过滤后的列表
	 * 
	 * @param persons
	 * @return
	 */
	List<Person> meetCriteria(List<Person> persons);
}
