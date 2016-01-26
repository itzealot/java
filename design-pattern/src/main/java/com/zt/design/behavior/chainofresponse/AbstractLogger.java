package com.zt.design.behavior.chainofresponse;

/**
 * 创建抽象的记录器类
 * 
 * @author zengtao
 *
 */
public abstract class AbstractLogger {
	public static final int INFO = 1;
	public static final int DEBUG = 2;
	public static final int ERROR = 3;

	// 子类可用于继承
	protected int level;

	// 责任链中的下一个元素
	protected AbstractLogger nextLogger;

	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public void logMessage(int level, String message) {
		if (this.level <= level) {
			write(message);
		}

		// 还有日志信息，继续显示nextLogger 的日志信息，递归调用实现
		if (nextLogger != null) {
			nextLogger.logMessage(level, message);
		}
	}

	abstract protected void write(String message);

}
