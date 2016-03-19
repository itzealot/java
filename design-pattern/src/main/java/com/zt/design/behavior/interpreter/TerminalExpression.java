package com.zt.design.behavior.interpreter;

/**
 * 包含表达式
 * 
 * @author zengtao
 *
 */
public class TerminalExpression implements Expression {
	// 数据
	private String data;

	public TerminalExpression(String data) {
		this.data = data;
	}

	/**
	 * 表达式间是包含(INCLUDE)运算
	 */
	public boolean interpret(String context) {
		if (context.contains(data)) {
			return true;
		}

		return false;
	}

}
