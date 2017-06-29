package com.sky.projects.design.structural.adapter.old;

/**
 * Media Player
 * 
 * @author zealot
 *
 */
public class OldMediaPlayer implements OldPlayer {
	private OldPlayer player;

	public OldMediaPlayer() {
	}

	public OldPlayer register(OldPlayer player) {
		this.player = player;
		return this;
	}

	@Override
	public void play(String fileName) {
		player.play(fileName);
	}

}
