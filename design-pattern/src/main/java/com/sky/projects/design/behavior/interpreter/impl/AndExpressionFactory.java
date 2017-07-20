package com.sky.projects.design.behavior.interpreter.impl;

import com.sky.projects.design.behavior.interpreter.Expression;
import com.sky.projects.design.behavior.interpreter.ExpressionFactory;

/**
 * AndExpressionFactory，用于反射创创建 AndExpression
 * 
 * @author zealot
 */
public class AndExpressionFactory implements ExpressionFactory {

	@Override
	public Expression getExpression(Expression e1, Expression e2) {
		return new AndExpression(e1, e2);
	}

}
