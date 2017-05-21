package com.sky.projects.design.structural.adapter;

/**
 * 适配器类(适配新增播放的功能)
 * 
 * @author zealot
 *
 */
public class MediaAdapter implements MediaPlayer {

	private AdvancedMediaPlayer palyer;

	/**
	 * 构造函数<br />
	 * 根据audio's type 创建高级媒体播放器对象
	 * 
	 * @param audioType
	 */
	public MediaAdapter(String audioType) {
		if (audioType.equalsIgnoreCase("vlc")) {
			palyer = new VlcPlayer();
		} else if (audioType.equalsIgnoreCase("mp4")) {
			palyer = new Mp4Player();
		}
	}

	@Override
	public void play(String audioType, String fileName) {
		// 播放vlc 与 mp4
		if (audioType.equalsIgnoreCase("vlc")) {
			palyer.playVlc(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			palyer.playMp4(fileName);
		}
	}
}
