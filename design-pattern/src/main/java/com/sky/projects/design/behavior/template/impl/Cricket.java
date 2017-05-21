package com.sky.projects.design.behavior.template.impl;

import com.sky.projects.design.behavior.template.Game;

/**
 * Cricket 游戏
 * 
 * @author zealot
 *
 */
public class Cricket extends Game {

	@Override
	public void endPlay() {
		System.out.println("Cricket Game Finished!");
	}

	@Override
	public void initialize() {
		System.out.println("Cricket Game Initialized! Start playing.");
	}

	@Override
	public void startPlay() {
		System.out.println("Cricket Game Started. Enjoy the game!");
	}
}