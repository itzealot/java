package com.sky.projects.design.behavior.interpreter;

/**
 * ExpressionFactory
 * 
 * @author zealot
 */
public interface ExpressionFactory {

	/**
	 * 根据两个表达式创建出新的表达式
	 * 
	 * @param e1
	 * @param e2
	 * @return
	 */
	Expression getExpression(Expression e1, Expression e2);

}
