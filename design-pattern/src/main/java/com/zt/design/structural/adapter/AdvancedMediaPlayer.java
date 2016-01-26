package com.zt.design.structural.adapter;

/**
 * 高级的媒体播放器接口
 * 
 * @author zengtao
 *
 */
public interface AdvancedMediaPlayer {
	/**
	 * To play Vlc audio by file's name
	 * 
	 * @param fileName
	 */
	public void playVlc(String fileName);

	/**
	 * To play mp4 audio by file's name
	 * 
	 * @param fileName
	 */
	public void playMp4(String fileName);
}