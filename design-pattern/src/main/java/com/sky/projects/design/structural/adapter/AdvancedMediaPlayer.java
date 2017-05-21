package com.sky.projects.design.structural.adapter;

/**
 * 高级的媒体播放器接口
 * 
 * @author zealot
 *
 */
public interface AdvancedMediaPlayer {

	/**
	 * To play Vlc audio by file's name
	 * 
	 * @param fileName
	 */
	void playVlc(String fileName);

	/**
	 * To play mp4 audio by file's name
	 * 
	 * @param fileName
	 */
	void playMp4(String fileName);
}