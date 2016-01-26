package com.zt.design.behavior.template;

/**
 * class Cricket extends Game.<br />
 * 创建扩展了上述类的实体类。
 * 
 * @author zengtao
 *
 */
public class Cricket extends Game {

	@Override
	void endPlay() {
		System.out.println("Cricket Game Finished!");
	}

	@Override
	void initialize() {
		System.out.println("Cricket Game Initialized! Start playing.");
	}

	@Override
	void startPlay() {
		System.out.println("Cricket Game Started. Enjoy the game!");
	}
}