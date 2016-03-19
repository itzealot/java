package com.zt.design.behavior.interpreter;

/**
 * 或表达式
 * 
 * @author zengtao
 *
 */
public class OrExpression implements Expression {

	// 表达式1
	private Expression expr1 = null;

	// 表达式2
	private Expression expr2 = null;

	public OrExpression(Expression expr1, Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * 表达式间是或运算
	 */
	public boolean interpret(String context) {
		return expr1.interpret(context) || expr2.interpret(context);
	}
}