package com.sky.projects.design.behavior.template.impl;

import com.sky.projects.design.behavior.template.Game;

/**
 * 足球游戏
 * 
 * @author zealot
 *
 */
public class Football extends Game {

	@Override
	public void endPlay() {
		System.out.println("Football Game Finished!");
	}

	@Override
	public void initialize() {
		System.out.println("Football Game Initialized! Start playing.");
	}

	@Override
	public void startPlay() {
		System.out.println("Football Game Started. Enjoy the game!");
	}
}