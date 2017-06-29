package com.sky.projects.design.structural.adapter;

public class CommonPlayer implements NewPlayer, MediaAdapter {

	private AudioEnum audioEnum;

	@Override
	public void play(AudioEnum audioEnum, String fileName) {
		this.audioEnum = audioEnum;

		if (AudioEnum.MP3.equals(audioEnum)) {
			// TODO mp3 播放实现，此处使用输出代替
			System.out.println("play mp3, fileName:" + fileName);
		} else {
			// TODO 其他实现类，此处使用输出代替
			System.out.println("play others, fileName:" + fileName);
		}
	}

	@Override
	public NewPlayer getPlayer() {
		return this;
	}

	@Override
	public AudioEnum getAudioEnum() {
		return audioEnum;
	}

}
