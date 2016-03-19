package com.zt.design.behavior.chainofresponse;

/**
 * 控制台日志
 * 
 * @author zengtao
 *
 */
public class ConsoleLogger extends AbstractLogger {

	public ConsoleLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Standard Console::Logger: " + message);
	}
}
