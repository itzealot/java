package com.zt.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * @Data ：注解在类上；提供类所有属性的 getting 和 setting
 *       方法，此外还提供了equals、canEqual、hashCode、toString 方法
 * 
 * @Setter：注解在属性上；为属性提供 setting 方法
 * 
 * @Getter：注解在属性上；为属性提供 getting 方法
 * 
 * @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 * 
 * @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
 * 
 * @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 * 
 * @author zengtao
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private String id;
	private String name;
	private String identity;

	/**
	 * To get the Looger object.
	 * 
	 * @return
	 */
	public org.apache.log4j.Logger getLog() {
		return log;
	}

}