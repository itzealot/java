package com.zt.design.behavior.template;

/**
 * Cricket 游戏
 * 
 * @author zengtao
 *
 */
public class Cricket extends Game {

	void endPlay() {
		System.out.println("Cricket Game Finished!");
	}

	void initialize() {
		System.out.println("Cricket Game Initialized! Start playing.");
	}

	void startPlay() {
		System.out.println("Cricket Game Started. Enjoy the game!");
	}
}