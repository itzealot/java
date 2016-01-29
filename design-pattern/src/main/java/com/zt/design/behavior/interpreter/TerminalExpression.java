package com.zt.design.behavior.interpreter;

/**
 * class TerminalExpression implements Expression.<br />
 * 包含表达式
 * 
 * @author zengtao
 *
 */
public class TerminalExpression implements Expression {
	private String data;

	public TerminalExpression(String data) {
		this.data = data;
	}

	/**
	 * 表达式间是 包含(INCLUDE)
	 */
	@Override
	public boolean interpret(String context) {
		if (context.contains(data)) {
			return true;
		}
		return false;
	}

}
