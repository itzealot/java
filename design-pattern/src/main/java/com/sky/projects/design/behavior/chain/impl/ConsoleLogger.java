package com.sky.projects.design.behavior.chain.impl;

import com.sky.projects.design.behavior.chain.AbstractLogger;

/**
 * 控制台日志
 * 
 * @author zealot
 */
public class ConsoleLogger extends AbstractLogger {

	public ConsoleLogger(int level) {
		super(level);
	}

	@Override
	protected void write(String message) {
		System.out.println("Standard Console::Logger: " + message);
	}
}
