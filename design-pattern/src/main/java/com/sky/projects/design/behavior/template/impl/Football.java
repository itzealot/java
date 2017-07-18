package com.sky.projects.design.behavior.template.impl;

import com.sky.projects.design.behavior.template.Game;

public class Football extends Game {

	@Override
	public void start() {
		System.out.println("Football Game start.");
	}

	@Override
	public void initialize() {
		System.out.println("Football Game initialize.");
	}

	@Override
	public void destroy() {
		System.out.println("Football Game destroy.");
	}
}