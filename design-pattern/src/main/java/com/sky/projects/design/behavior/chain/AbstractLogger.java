package com.sky.projects.design.behavior.chain;

/**
 * 日志链(责任链)
 * 
 * @author zealot
 */
public abstract class AbstractLogger {
	// 记录当前日志的日志级别
	protected int level;

	// 日志链表
	protected AbstractLogger nextLogger;

	public AbstractLogger(int level) {
		this.level = level;
	}

	/**
	 * 打印日志链中的日志信息，传入的日志级别大于等于日志链中的日志级别，则打印
	 * 
	 * @param level
	 * @param message
	 */
	public final void logMessage(int level, String message) {
		// 当前日志对象的日志级别小于或等于传入的日志级别
		if (this.level <= level) {
			write(message);
		}

		// 递归打印日志
		if (nextLogger != null) {
			nextLogger.logMessage(level, message);
		}
	}

	/**
	 * 打印日志
	 * 
	 * @param message
	 */
	abstract protected void write(String message);

	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}
}
