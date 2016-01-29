package com.zt.design.behavior.chainofresponse;

/**
 * 创建扩展了该记录器类的实体类
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
