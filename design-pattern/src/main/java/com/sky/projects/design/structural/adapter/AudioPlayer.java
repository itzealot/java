package com.sky.projects.design.structural.adapter;

/**
 * 播放器播实现主类
 * 
 * @author zealot
 *
 */
public class AudioPlayer implements MediaPlayer {
	private MediaAdapter mediaAdapter;

	@Override
	public void play(String audioType, String fileName) {
		// 播放 mp3 音乐文件的内置支持
		if (audioType.equalsIgnoreCase("mp3")) {
			System.out.println("Playing mp3 file. Name: " + fileName);
		} else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
			// mediaAdapter 提供了播放其他文件格式的支持
			// 创建实现了 MediaPlayer 接口的适配器类 对象
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		} else {
			System.out.println("Invalid media. " + audioType + " format not supported");
		}
	}
}