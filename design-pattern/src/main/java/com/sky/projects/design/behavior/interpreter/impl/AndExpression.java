package com.sky.projects.design.behavior.interpreter.impl;

import com.sky.projects.design.behavior.interpreter.Expression;

/**
 * 与表达式(表达式需全部满足)
 * 
 * @author zealot
 */
public class AndExpression implements Expression {

	// 表达式1
	private Expression expr1;

	// 表达式2
	private Expression expr2;

	public AndExpression(Expression expr1, Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	@Override
	public boolean interpret(String context) {
		return expr1.interpret(context) && expr2.interpret(context);
	}
}