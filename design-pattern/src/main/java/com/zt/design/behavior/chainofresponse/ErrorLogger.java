package com.zt.design.behavior.chainofresponse;

/**
 * 错误日志
 * 
 * @author zt
 */
public class ErrorLogger extends AbstractLogger {

	public ErrorLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Error Console::Logger: " + message);
	}
}