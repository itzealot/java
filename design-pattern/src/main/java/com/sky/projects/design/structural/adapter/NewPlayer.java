package com.sky.projects.design.structural.adapter;

/**
 * NewPlayer，新的播放器接口
 * 
 * @author zealot
 *
 */
public interface NewPlayer {

	/**
	 * 
	 * @param audioEnum
	 * @param fileName
	 */
	void play(AudioEnum audioEnum, String fileName);
}
