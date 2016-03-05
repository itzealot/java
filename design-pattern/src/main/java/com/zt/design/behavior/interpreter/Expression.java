package com.zt.design.behavior.interpreter;

/**
 * 表达式接口
 * 
 * @author zengtao
 *
 */
public interface Expression {
	/**
	 * 表达式匹配规则，根据传入的信息进行匹配，若匹配，返回true；否则返回false
	 * 
	 * @param context
	 * @return
	 */
	public boolean interpret(String context);
}
