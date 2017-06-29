package com.sky.projects.design.structural.adapter;

public enum AudioEnum {
	MP4("mp4"), MP3("mp3"), VLC("vlc"), OTHERS("others");

	private final String suffix;

	private AudioEnum(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

}
