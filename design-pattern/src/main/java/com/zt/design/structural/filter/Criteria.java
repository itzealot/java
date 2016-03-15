package com.zt.design.structural.filter;

import java.util.List;

/**
 * 过滤接口
 * 
 * @author zengtao
 *
 */
public interface Criteria {

	/**
	 * 根据传入的列表进行过滤，并返回过滤后的列表
	 * 
	 * @param persons
	 * @return
	 */
	public List<Person> meetCriteria(List<Person> persons);
}
