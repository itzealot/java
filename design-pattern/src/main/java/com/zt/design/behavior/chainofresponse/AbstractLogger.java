package com.zt.design.behavior.chainofresponse;

/**
 * 创建抽象的记录器类
 * 
 * @author zengtao
 *
 */
public abstract class AbstractLogger {

	// INFO 日志级别
	public static final int INFO = 1;

	// DEBUG 日志级别
	public static final int DEBUG = 2;

	// ERROR 日志级别
	public static final int ERROR = 3;

	// 记录当前日志实体的日志级别
	protected int level;

	// 责任链中的下一个元素
	protected AbstractLogger nextLogger;

	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}

	/**
	 * 打印日志信息
	 * 
	 * @param level
	 * @param message
	 */
	public void logMessage(int level, String message) {
		// 当前日志对象的日志级别小于或等于传入的日志级别
		if (this.level <= level) {
			// 打印日志
			write(message);
		}

		// 还有日志信息，继续显示nextLogger 的日志信息，递归调用实现
		if (nextLogger != null) {
			nextLogger.logMessage(level, message);
		}
	}

	/**
	 * 打印信息，抽象方法，用于子类实现
	 * 
	 * @param message
	 */
	abstract protected void write(String message);

}
