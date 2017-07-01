package com.sky.projects.design.behavior.chain.impl;

import com.sky.projects.design.behavior.chain.AbstractLogger;

/**
 * 错误日志
 * 
 * @author zealot
 */
public class ErrorLogger extends AbstractLogger {

	public ErrorLogger(int level) {
		super(level);
	}

	@Override
	protected void write(String message) {
		System.out.println("Error Console::Logger: " + message);
	}
}