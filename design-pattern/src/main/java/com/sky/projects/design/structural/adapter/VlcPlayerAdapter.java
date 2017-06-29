package com.sky.projects.design.structural.adapter;

import com.sky.projects.design.structural.adapter.old.VlcPlayer;

public class VlcPlayerAdapter implements MediaAdapter {

	@Override
	public NewPlayer getPlayer() {
		return new PlayerWrapper(new VlcPlayer());
	}

	@Override
	public AudioEnum getAudioEnum() {
		return AudioEnum.VLC;
	}

}
