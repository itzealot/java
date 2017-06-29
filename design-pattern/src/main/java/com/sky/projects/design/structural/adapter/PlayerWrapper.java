package com.sky.projects.design.structural.adapter;

import com.sky.projects.design.structural.adapter.old.OldPlayer;

/**
 * 
 * @author zealot
 *
 */
public class PlayerWrapper implements NewPlayer {

	private OldPlayer player;

	public PlayerWrapper(OldPlayer player) {
		this.player = player;
	}

	@Override
	public void play(AudioEnum audioEnum, String fileName) {
		player.play(fileName);
	}
}
