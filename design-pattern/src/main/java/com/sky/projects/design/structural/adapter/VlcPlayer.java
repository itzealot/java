package com.sky.projects.design.structural.adapter;

/**
 * Vlc播放器
 * 
 * @author zealot
 *
 */
public class VlcPlayer implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		// there is nothing to do
	}
}
