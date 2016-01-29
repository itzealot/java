package com.zt.design.behavior.interpreter;

/**
 * class AndExpression implements Expression.<br />
 * 与表达式
 * 
 * @author zengtao
 *
 */
public class AndExpression implements Expression {

	private Expression expr1 = null;
	private Expression expr2 = null;

	public AndExpression(Expression expr1, Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * 表达式间是 AND
	 */
	@Override
	public boolean interpret(String context) {
		return expr1.interpret(context) && expr2.interpret(context);
	}
}