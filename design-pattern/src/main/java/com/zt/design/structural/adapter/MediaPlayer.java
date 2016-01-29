package com.zt.design.structural.adapter;

/**
 * 媒体播放器接口。
 * 
 * @author zengtao
 *
 */
public interface MediaPlayer {
	/**
	 * To play the audio by aodio's type and file's name
	 * 
	 * @param audioType
	 * @param fileName
	 */
	public void play(String audioType, String fileName);
}
