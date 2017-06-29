package com.sky.projects.design.structural.adapter;

import com.sky.projects.design.structural.adapter.old.Mp4Player;

public class Mp4PlayerAdapter implements MediaAdapter {

	@Override
	public AudioEnum getAudioEnum() {
		return AudioEnum.MP4;
	}

	@Override
	public NewPlayer getPlayer() {
		return new PlayerWrapper(new Mp4Player());
	}

}
