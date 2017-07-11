package com.sky.projects.jdk.design;

/**
 * MessageExecutor
 * 
 * @author zealot
 */
public interface MessageConsumer {

	/**
	 * consume a String message
	 * 
	 * @param message
	 */
	void consume(String message);
}
