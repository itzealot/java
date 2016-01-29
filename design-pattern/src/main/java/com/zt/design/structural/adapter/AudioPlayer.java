package com.zt.design.structural.adapter;

/**
 * 实现了 MediaPlayer 接口的实体类。<br />
 * Audio播放的实现类
 * 
 * @author zengtao
 *
 */
public class AudioPlayer implements MediaPlayer {
	private MediaAdapter mediaAdapter;

	/**
	 * 播放本身支持的格式的同时，也利用适配器支持的类型播放Audio
	 */
	public void play(String audioType, String fileName) {

		// 播放 mp3 音乐文件的内置支持
		if (audioType.equalsIgnoreCase("mp3")) {
			System.out.println("Playing mp3 file. Name: " + fileName);
		}// mediaAdapter 提供了播放其他文件格式的支持
		else if (audioType.equalsIgnoreCase("vlc")
				|| audioType.equalsIgnoreCase("mp4")) {
			// 创建实现了 MediaPlayer 接口的适配器类 对象
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		} else {
			System.out.println("Invalid media. " + audioType
					+ " format not supported");
		}
	}
}