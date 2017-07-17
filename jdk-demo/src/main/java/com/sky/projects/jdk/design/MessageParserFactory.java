package com.sky.projects.jdk.design;

/**
 * MessageParserFactory
 * 
 * @author zealot
 */
public interface MessageParserFactory {

	/**
	 * get parser by name
	 * 
	 * @param name
	 * @return
	 */
	MessageParser getParser(String name);

}
