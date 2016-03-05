package com.zt.design.behavior.chainofresponse;

/**
 * 文件日志
 * 
 * @author zt
 */
public class FileLogger extends AbstractLogger {

	public FileLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("File::Logger: " + message);
	}
}