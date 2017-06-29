package com.sky.projects.design.structural.adapter.old;

/**
 * 原有的播放器模式
 * 
 * @author zealot
 *
 */
public class MediaPlayerDemo {

	public static void main(String[] args) {
		OldMediaPlayer player = new OldMediaPlayer();

		// 播放 mp4
		player.register(new Mp4Player()).play("video.mp4");

		// 播放 vlc
		player.register(new VlcPlayer()).play("video.vlc");

	}
}
