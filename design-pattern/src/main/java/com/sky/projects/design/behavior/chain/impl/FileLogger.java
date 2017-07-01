package com.sky.projects.design.behavior.chain.impl;

import com.sky.projects.design.behavior.chain.AbstractLogger;

/**
 * 文件日志
 * 
 * @author zealot
 */
public class FileLogger extends AbstractLogger {

	public FileLogger(int level) {
		super(level);
	}

	@Override
	protected void write(String message) {
		System.out.println("File::Logger: " + message);
	}
}