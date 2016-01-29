package com.zt.design.structural.adapter;

/**
 * 实现了 MediaPlayer 接口的适配器类。
 * 
 * @author zengtao
 *
 */
public class MediaAdapter implements MediaPlayer {

	private AdvancedMediaPlayer advancedMusicPlayer;

	/**
	 * 构造函数<br />
	 * 根据audio's type 创建高级媒体播放器对象
	 * 
	 * @param audioType
	 */
	public MediaAdapter(String audioType) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer = new VlcPlayer();
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer = new Mp4Player();
		}
	}

	// 播放vlc 与 mp4
	public void play(String audioType, String fileName) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer.playVlc(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer.playMp4(fileName);
		}
	}
}
