package com.sky.projects.design.structural.adapter;

/**
 * mp4播放器
 * 
 * @author zealot
 *
 */
public class Mp4Player implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		// there is nothing to do
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: " + fileName);
	}
}