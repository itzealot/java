package com.zt.design.structural.adapter;

/**
 * The Mp4Player implements AdvancedMediaPlayer
 * 
 * @author zengtao
 *
 */
public class Mp4Player implements AdvancedMediaPlayer {

	public void playVlc(String fileName) {
		// there is nothing to do
	}

	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: " + fileName);
	}
}