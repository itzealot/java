package com.sky.projects.design.behavior.interpreter.impl;

import com.sky.projects.design.behavior.interpreter.Expression;
import com.sky.projects.design.behavior.interpreter.ExpressionFactory;

/**
 * OrExpressionFactory，用于反射创建 OrExpression
 * 
 * @author zealot
 */
public class OrExpressionFactory implements ExpressionFactory {

	@Override
	public Expression getExpression(Expression e1, Expression e2) {
		return new OrExpression(e1, e2);
	}

}
