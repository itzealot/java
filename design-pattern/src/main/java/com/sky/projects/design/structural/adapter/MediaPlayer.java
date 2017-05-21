package com.sky.projects.design.structural.adapter;

/**
 * 媒体播放器接口
 * 
 * @author zealot
 *
 */
public interface MediaPlayer {

	/**
	 * To play the audio by aodio's type and file's name
	 * 
	 * @param audioType
	 * @param fileName
	 */
	void play(String audioType, String fileName);
}
