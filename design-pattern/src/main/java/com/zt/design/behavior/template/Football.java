package com.zt.design.behavior.template;

/**
 * 足球游戏
 * 
 * @author zengtao
 *
 */
public class Football extends Game {

	void endPlay() {
		System.out.println("Football Game Finished!");
	}

	void initialize() {
		System.out.println("Football Game Initialized! Start playing.");
	}

	void startPlay() {
		System.out.println("Football Game Started. Enjoy the game!");
	}
}