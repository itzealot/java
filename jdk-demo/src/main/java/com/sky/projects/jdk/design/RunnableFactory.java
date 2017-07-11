package com.sky.projects.jdk.design;

/**
 * RunnableFactory
 * 
 * @author zealot
 */
public interface RunnableFactory {

	Runnable newRunnable(AppContext context);
}
