package com.sky.projects.design.structural.adapter.old;

/**
 * 专门播放 mp4 的播放器
 * 
 * @author zealot
 *
 */
public class Mp4Player implements OldPlayer {

	@Override
	public void play(String fileName) {
		System.out.println("play mp4, fileName:" + fileName);
	}
}