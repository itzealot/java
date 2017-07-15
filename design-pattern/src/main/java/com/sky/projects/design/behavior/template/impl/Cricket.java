package com.sky.projects.design.behavior.template.impl;

import com.sky.projects.design.behavior.template.Game;

/**
 * Cricket 游戏
 * 
 * @author zealot
 */
public class Cricket extends Game {

	@Override
	public void destroy() {
		System.out.println("Cricket Game destroy.");
	}

	@Override
	public void initialize() {
		System.out.println("Cricket Game initialize.");
	}

	@Override
	public void start() {
		System.out.println("Cricket Game start.");
	}
}