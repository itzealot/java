package com.sky.projects.jdk.design;

import java.util.concurrent.BlockingQueue;

/**
 * AppContext
 * 
 * @author zealot
 */
public interface AppContext {

	BlockingQueue<String> getBlockingQueue();

	String get(String key);

	String get(String key, String defaultValue);

}
