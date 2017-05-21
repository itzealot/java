package com.sky.projects.design.behavior.interpreter.impl;

import com.sky.projects.design.behavior.interpreter.Expression;

/**
 * 包含表达式(单一匹配)
 * 
 * @author zealot
 */
public class TerminalExpression implements Expression {
	// 数据
	private String data;

	public TerminalExpression(String data) {
		this.data = data;
	}

	@Override
	public boolean interpret(String context) {
		return context.contains(data);
	}

}
