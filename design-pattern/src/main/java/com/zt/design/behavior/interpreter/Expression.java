package com.zt.design.behavior.interpreter;

/**
 * 创建表达式接口
 * 
 * @author zengtao
 *
 */
public interface Expression {
	/**
	 * 表达式匹配规则
	 * 
	 * @param context
	 * @return
	 */
	public boolean interpret(String context);
}
