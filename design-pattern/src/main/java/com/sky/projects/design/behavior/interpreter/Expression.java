package com.sky.projects.design.behavior.interpreter;

/**
 * 表达式接口
 * 
 * @author zealot
 *
 */
public interface Expression {

	/**
	 * 表达式匹配规则，根据传入的信息进行匹配，若匹配，返回true；否则返回false
	 * 
	 * @param context
	 * @return
	 */
	boolean interpret(String context);
}
