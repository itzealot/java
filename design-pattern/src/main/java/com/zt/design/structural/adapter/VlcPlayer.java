package com.zt.design.structural.adapter;

/**
 * The VlcPlayer implements AdvancedMediaPlayer
 * 
 * @author zengtao
 *
 */
public class VlcPlayer implements AdvancedMediaPlayer {
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	public void playMp4(String fileName) {
		// there is nothing to do
	}
}
