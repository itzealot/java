package com.sky.projects.util;

/**
 * Runnable to use create Thread
 * 
 * @author zealot
 */
public interface SkyRunnable extends Runnable {

	/**
	 * The Thread run method
	 */
	public void doRun();

	/**
	 * stop run thread
	 */
	public void stop();
}
