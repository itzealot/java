package com.sky.projects.design.structural.adapter.old;

/**
 * 专门播放 vlc 的播放器
 * 
 * @author zealot
 *
 */
public class VlcPlayer implements OldPlayer {

	@Override
	public void play(String fileName) {
		System.out.println("play vlc, fileName:" + fileName);
	}
}
