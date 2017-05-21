package com.sky.projects.design.behavior.state.impl;

import com.sky.projects.design.behavior.state.Context;
import com.sky.projects.design.behavior.state.State;

/**
 * 开始状态实现类
 * 
 * @author zealot
 *
 */
public class StartState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("Player is in start state");
		context.setState(this);
	}

	@Override
	public String toString() {
		return "Start State";
	}
}