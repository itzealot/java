package com.sky.projects.design.structural.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 播放器播实现主类，根据不同的适配器选择播放不同的文件
 * 
 * @author zealot
 *
 */
public class NewMediaPlayer implements NewPlayer {

	private static final Map<AudioEnum, MediaAdapter> adapters = new HashMap<>();

	static {
		adapters.put(AudioEnum.VLC, new VlcPlayerAdapter());
		adapters.put(AudioEnum.MP4, new Mp4PlayerAdapter());
		adapters.put(AudioEnum.MP3, new CommonPlayer());
		adapters.put(AudioEnum.OTHERS, new CommonPlayer());
	}

	@Override
	public void play(AudioEnum audioEnum, String fileName) {
		MediaAdapter adapter = adapters.get(audioEnum);

		if (adapter != null) {
			adapter.getPlayer().play(audioEnum, fileName);
		}
	}

}